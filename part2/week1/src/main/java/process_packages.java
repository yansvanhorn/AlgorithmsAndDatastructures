import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (dropped != response.dropped) return false;
        return start_time == response.start_time;

    }

    @Override
    public int hashCode() {
        int result = (dropped ? 1 : 0);
        result = 31 * result + start_time;
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                 dropped +
                ", " + start_time +
                '}';
    }
}

class Buffer {
    ArrayDeque<Integer> buffer;
    private int size;
    private int finishTime;

    public Buffer(int size) {
        this.buffer = new ArrayDeque<>(size);
        this.size = size;
        this.finishTime = 0;
    }

    public Response Process(Request request) {
        // clean buffer of old...

        while(buffer.peekLast() != null && buffer.peekLast() <= request.arrival_time) {
            buffer.pollLast();
        }

        // process arriving
        Response response;

        if(buffer.size() == 0) {
            response = new Response(false, request.arrival_time);
            buffer.addFirst(request.arrival_time + request.process_time);
        } else if(buffer.size() < size) {
            response = new Response(false, buffer.peekFirst());
            buffer.addFirst(buffer.peekFirst() + request.process_time);
        } else {
            response = new Response(true, -1);
        }

        // write your code here
        return response;
    }

}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    public static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
