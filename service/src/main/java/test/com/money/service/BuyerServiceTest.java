package test.com.money.service;

import com.money.model.Buyer;
import com.money.service.BuyerService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Date;

/**
 * BuyerService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 22, 2016</pre>
 */
public class BuyerServiceTest {
    BuyerService service = new BuyerService();
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: queryByPK(long id)
     */
    @Test
    public void testQueryByPK() throws Exception {
        Buyer buyer = service.queryByPK(1);
        System.out.println(buyer);

    }

    /**
     * Method: insert(Buyer entity)
     */
    @Test
    public void testInsert() throws Exception {
        Buyer buyer = new Buyer();
        //buyer.setBuyerId(1L);
        buyer.setBuyerName("buyer");
        buyer.setAddTime(new Date());
        service.insert(buyer);
    }

    /**
     * Method: update(Buyer entity)
     */
    @Test
    public void testUpdate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: delete(long id)
     */
    @Test
    public void testDelete() throws Exception {
//TODO: Test goes here... 
    }


} 
