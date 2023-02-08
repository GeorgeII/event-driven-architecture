package model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Receiver extends Transaction {

    private Long blockId;
    private String blockHash;
    private String transactionAddress;
    private String receiverEntity;
    private BigDecimal receiverAmount;
    private String receiverAsset;
    private String type;
    private String blockTime;
    private UUID correlationId;

    public Receiver(Long blockId, String blockHash, String transactionAddress, String receiverEntity, BigDecimal receiverAmount, String receiverAsset, String type, String blockTime) {
        this.blockId = blockId;
        this.blockHash = blockHash;
        this.transactionAddress = transactionAddress;
        this.receiverEntity = receiverEntity;
        this.receiverAmount = receiverAmount;
        this.receiverAsset = receiverAsset;
        this.type = type;
        this.blockTime = blockTime;
    }

    public Receiver(Long blockId, String blockHash, String transactionAddress, String receiverEntity, BigDecimal receiverAmount, String receiverAsset, String type, String blockTime, UUID correlationId) {
        this.blockId = blockId;
        this.blockHash = blockHash;
        this.transactionAddress = transactionAddress;
        this.receiverEntity = receiverEntity;
        this.receiverAmount = receiverAmount;
        this.receiverAsset = receiverAsset;
        this.type = type;
        this.blockTime = blockTime;
        this.correlationId = correlationId;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getTransactionAddress() {
        return transactionAddress;
    }

    public void setTransactionAddress(String transactionAddress) {
        this.transactionAddress = transactionAddress;
    }

    public String getReceiverEntity() {
        return receiverEntity;
    }

    public void setReceiverEntity(String receiverEntity) {
        this.receiverEntity = receiverEntity;
    }

    public BigDecimal getReceiverAmount() {
        return receiverAmount;
    }

    public void setReceiverAmount(BigDecimal receiverAmount) {
        this.receiverAmount = receiverAmount;
    }

    public String getReceiverAsset() {
        return receiverAsset;
    }

    public void setReceiverAsset(String receiverAsset) {
        this.receiverAsset = receiverAsset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(String blockTime) {
        this.blockTime = blockTime;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "blockId=" + blockId +
                ", blockHash='" + blockHash + '\'' +
                ", transactionAddress='" + transactionAddress + '\'' +
                ", receiverEntity='" + receiverEntity + '\'' +
                ", receiverAmount=" + receiverAmount +
                ", receiverAsset='" + receiverAsset + '\'' +
                ", type='" + type + '\'' +
                ", blockTime=" + blockTime +
                ", correlationId=" + correlationId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(blockId, receiver.blockId) && Objects.equals(blockHash, receiver.blockHash) && Objects.equals(transactionAddress, receiver.transactionAddress) && Objects.equals(receiverEntity, receiver.receiverEntity) && Objects.equals(receiverAmount, receiver.receiverAmount) && Objects.equals(receiverAsset, receiver.receiverAsset) && Objects.equals(type, receiver.type) && Objects.equals(blockTime, receiver.blockTime) && Objects.equals(correlationId, receiver.correlationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, blockHash, transactionAddress, receiverEntity, receiverAmount, receiverAsset, type, blockTime, correlationId);
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }
}
