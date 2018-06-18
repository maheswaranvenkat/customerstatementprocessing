package com.rabo.model;

import com.rabo.validation.ValidateEndBalance;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@XmlRootElement(name="record")
public class Record {
    private String reference;
    private String accountNumber;
    private String description;
    private String startBalance;
    private String mutation;
    private String endBalance;

    @XmlElement(name="reference")
    public Record setReference(String reference) {
        this.reference = reference;
        return this;
    }
    @XmlElement(name="accountNumber")
    public Record setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }
    @XmlElement(name="description")
    public Record setDescription(String description) {
        this.description = description;
        return this;
    }
    @XmlElement(name="startBalance")
    public Record setStartBalance(String startBalance) {
        this.startBalance = startBalance;
        return this;
    }
    @XmlElement(name="mutation")
    public Record setMutation(String mutation) {
        this.mutation = mutation;
        return this;
    }

    @ValidateEndBalance(min=0, message="Amount field should not be empty")
    @NotNull
    @XmlElement(name="endBalance")
    public Record setEndBalance(String endBalance) {
        this.endBalance = endBalance;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getStartBalance() {
        return startBalance;
    }

    public String getMutation() {
        return mutation;
    }

    public String getEndBalance() {
        return endBalance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Record that = (Record) o;

        return new EqualsBuilder()
                .append(reference, that.reference)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(reference)
                .toHashCode();
    }
}
