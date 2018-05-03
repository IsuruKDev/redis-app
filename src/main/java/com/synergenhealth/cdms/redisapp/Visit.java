/**
 * Copyright Synergen Health 2015
 */
package com.synergenhealth.cdms.redisapp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Visit domain object which represents Visits collection in MongoDB
 *
 * @author Isuru Kularathna
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "visits")
public class Visit implements Serializable {

    private static final long serialVersionUID = -2393789714951638116L;

    @Id
    private String visitId;

    @Indexed
    private int licenceKey;

    @Indexed
    private Integer visitNumber;

    private Integer chartNumber;

    private String providerProfile;

    // Colector tab status
    private String status;

    // Collector tab followUp status
    private String fuStatus;

    // Collector tab is submitted or saved, latest update
    private String saveSubmitStatus;

    private LocalDateTime saveSubmitDate;

    // ActiveChargeDetail latest changedAt time in AMD
    private LocalDate lastTouchedDate;

    private boolean isGoneThroughWorkQueueManager;

    private LocalDate amdCreatedAt;

    private LocalDate dateOfServiceStartDate;

    private LocalDate dateOfServiceEndDate;

    private int agingDays;

    private Double totalCharges;

    private Double totalWriteOffAmount;

    private Double totalPayments;

    private Double totalRefund;

    private Double totalBalance;

    private LocalDate firstBilledDate;

    private LocalDate doe;

    private LocalDate dos;

    private Double patientBalance;

    private Double insuranceBalance;

    private BigDecimal totalPatientPortion;

    private BigDecimal totalInsurancePortion;

    // Last Billed carrier/PayorbigDecimal
    private String currentPayor;

    private String currentPayorPolicyId;

    private String visitState = "UNSETTLED";

    private String billingType;

    private String refLab;

    private String specimenCode;

    private String referringProvider;

    private Integer voidState;

    private String referringProviderNPI;

    private String insuranceBillingSequence;

    private LocalDate dateofInjury;

    private int touchedCount;

    private boolean isHold;

    private String deniedState;

    private LocalDate lastDenialDate;

    private int collectionPool = 0;

    private boolean isZeroBalanced = false;

    private int lastTouchedDateAgingDays;



}