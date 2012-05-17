package org.agilewiki.jfile.transactions.db.counter;

import junit.framework.TestCase;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jactor.JAMailboxFactory;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.MailboxFactory;
import org.agilewiki.jactor.factory.JAFactory;
import org.agilewiki.jfile.JFileFactories;
import org.agilewiki.jfile.transactions.logReader.LogReader;
import org.agilewiki.jfile.transactions.logReader.ReadLog;

import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CounterRecoveryTest extends TestCase {
    public void test()
            throws Exception {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(10);
        Mailbox factoryMailbox = mailboxFactory.createMailbox();
        JAFactory factory = new JAFactory(factoryMailbox);
        (new JFileFactories(factoryMailbox)).setParent(factory);
        factory.defineActorType("inc", IncrementCounterTransaction.class);
        JAFuture future = new JAFuture();

        Mailbox dbMailbox = mailboxFactory.createAsyncMailbox();
        CounterDB db = new CounterDB(dbMailbox);
        db.setParent(factory);

        LogReader logReader = db.getLogReader();
        Path path = FileSystems.getDefault().getPath("CounterTest.jf");
        System.out.println(path.toAbsolutePath());
        logReader.fileChannel = FileChannel.open(
                path,
                StandardOpenOption.READ);

        ReadLog.req.send(future, logReader);

        logReader.fileChannel.close();
        mailboxFactory.close();
    }
}
