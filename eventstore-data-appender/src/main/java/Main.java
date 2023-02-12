import com.eventstore.dbclient.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Receiver;
import model.Sender;
import model.Transaction;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        List<String> topics = Arrays.asList("senders", "receivers");

        EventStoreDBClientSettings setts = EventStoreDBConnectionString.parseOrThrow("esdb://eventstore-db:2113?tls=false");
        EventStoreDBClient client = EventStoreDBClient.create(setts);



        ObjectMapper mapper = new ObjectMapper();




        // Reading from Kafka below

        final Properties props = new Properties();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "esdb-writer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 10 * ConsumerConfig.DEFAULT_FETCH_MAX_BYTES);
//        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 50 * ConsumerConfig.DEFAULT_MAX_PARTITION_FETCH_BYTES);
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);

        // UNCOMMENT BELOW

        final Consumer<Long, String> consumer = new KafkaConsumer<>(props);

        try (consumer) {
            consumer.subscribe(topics);
            while (true) {
                final ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1_000));

                if (consumerRecords.count() == 0) {
                    System.out.println("No messages in batch");
                }

                for (var record: consumerRecords) {
//                    System.out.printf("Consumer Record:(%s, %d, %s, %d, %d)\n",
//                            record.topic(), record.key(), record.value(),
//                            record.partition(), record.offset());
                    JsonNode node = mapper.readTree(record.value());
//                    System.out.println(node);

                    Transaction tnx = null;
//                    System.out.println(record.topic());
                    if (record.topic().equals("receivers")) {
                        tnx = new Receiver(
                                Long.parseLong(node.get("block_id").asText()),
                                node.get("block_hash").asText(),
                                node.get("transaction_address").asText(),
                                node.get("receiver_entity").asText(),
                                new BigDecimal(node.get("receiver_amount").asText()),
                                node.get("receiver_asset").asText(),
                                node.get("type").asText(),
                                node.get("block_time").asText(),
                                UUID.fromString(node.get("correlation_id").asText())
                        );
                    } else if (record.topic().equals("senders")) {
                        tnx = new Sender(
                                Long.parseLong(node.get("block_id").asText()),
                                node.get("block_hash").asText(),
                                node.get("transaction_address").asText(),
                                node.get("sender_entity").asText(),
                                new BigDecimal(node.get("sender_amount").asText()),
                                node.get("sender_asset").asText(),
                                node.get("type").asText(),
                                node.get("block_time").asText(),
                                UUID.fromString(node.get("correlation_id").asText())
                        );
                    }

                    EventData event = EventData
                            .builderAsJson("transaction-committed", tnx)
                            .build();

                    WriteResult writeResult = client
                            .appendToStream("transactions", event)
                            .get();

                }

                consumer.commitAsync();
            }
        }


    }


}
