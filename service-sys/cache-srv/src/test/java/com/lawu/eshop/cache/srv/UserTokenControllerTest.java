package com.lawu.eshop.cache.srv;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lawu.eshop.cache.srv.controller.UserTokenController;

/**
 * @author Leach
 * @date 2017/6/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
@WebAppConfiguration
public class UserTokenControllerTest {
    private MockMvc mvc;

    @Autowired
    private UserTokenController userTokenController;

    private ExecutorService executorService = new ThreadPoolExecutor(3000, 3500, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userTokenController).build();
    }

    @Ignore
    @Test
    public void setMemberTokenOneToOne() throws Exception {
        RequestBuilder request = null;
        UUID key = UUID.randomUUID();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            String account = key + "-test" + i;
            String token = "test-token" + i;
            Future<Integer> future = executorService.submit(new Callable<Integer>() {

                @Override
                public Integer call() {
                    //System.out.println(account);
                    RequestBuilder request = put("/userToken/setMemberTokenOneToOne").param("account", account).param("token", token).param("expireSeconds", "3600");
                    try {
                        ResultActions perform = mvc.perform(request).andExpect(status().isOk());
                        return 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });

            try {
                System.out.println(future.get());


            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        System.out.printf("Total time: %s", System.currentTimeMillis() - startTime);
    }

}
