package model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Sender extends Transaction {

    private Long blockId;
    private String blockHash;
    private String transactionAddress;
    private String senderEntity;
    private BigDecimal senderAmount;
    private String senderAsset;
    private String type;
    private String blockTime;
    private UUID correlationId;

    public Sender(Long blockId, String blockHash, String transactionAddress, String senderEntity, BigDecimal senderAmount, String senderAsset, String type, String blockTime) {
        this.blockId = blockId;
        this.blockHash = blockHash;
        this.transactionAddress = transactionAddress;
        this.senderEntity = senderEntity;
        this.senderAmount = senderAmount;
        this.senderAsset = senderAsset;
        this.type = type;
        this.blockTime = blockTime;
    }

    public Sender(Long blockId, String blockHash, String transactionAddress, String senderEntity, BigDecimal senderAmount, String senderAsset, String type, String blockTime, UUID correlationId) {
        this.blockId = blockId;
        this.blockHash = blockHash;
        this.transactionAddress = transactionAddress;
        this.senderEntity = senderEntity;
        this.senderAmount = senderAmount;
        this.senderAsset = senderAsset;
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

    public String getSenderEntity() {
        return senderEntity;
    }

    public void setSenderEntity(String senderEntity) {
        this.senderEntity = senderEntity;
    }

    public BigDecimal getSenderAmount() {
        return senderAmount;
    }

    public void setSenderAmount(BigDecimal senderAmount) {
        this.senderAmount = senderAmount;
    }

    public String getSenderAsset() {
        return senderAsset;
    }

    public void setSenderAsset(String senderAsset) {
        this.senderAsset = senderAsset;
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

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "blockId=" + blockId +
                ", blockHash='" + blockHash + '\'' +
                ", transactionAddress='" + transactionAddress + '\'' +
                ", senderEntity='" + senderEntity + '\'' +
                ", senderAmount=" + senderAmount +
                ", senderAsset='" + senderAsset + '\'' +
                ", type='" + type + '\'' +
                ", blockTime=" + blockTime +
                ", correlationId=" + correlationId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sender sender = (Sender) o;
        return Objects.equals(blockId, sender.blockId) && Objects.equals(blockHash, sender.blockHash) && Objects.equals(transactionAddress, sender.transactionAddress) && Objects.equals(senderEntity, sender.senderEntity) && Objects.equals(senderAmount, sender.senderAmount) && Objects.equals(senderAsset, sender.senderAsset) && Objects.equals(type, sender.type) && Objects.equals(blockTime, sender.blockTime) && Objects.equals(correlationId, sender.correlationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, blockHash, transactionAddress, senderEntity, senderAmount, senderAsset, type, blockTime, correlationId);
    }
}
