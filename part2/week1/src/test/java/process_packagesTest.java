import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-11-20.
 */
public class process_packagesTest {

    @Test
    public void testProcessRequests() throws Exception {
            assertEquals(toResponses(0, 5, 10, 15, 20, 25, -1), process_packages.ProcessRequests(
                toRequests(
                    new int[][]{ {0, 5}, {1, 5}, {2, 5}, {3, 5}, {4, 5}, {5, 5}, {6, 5} }
                ), new Buffer(5)));
    }

    public ArrayList<Request> toRequests(int[][] in) {
        ArrayList requests = new ArrayList(in.length);
        for (int i = 0; i < in.length; i++) {
            requests.add(new Request(in[i][0], in[i][1]));
        }
        return requests;
    }

    public ArrayList<Response> toResponses(int... in) {
        ArrayList responses = new ArrayList(in.length);
        for(int i : in) {
            responses.add(new Response(i == -1, i));
        }
        return responses;
    }
}