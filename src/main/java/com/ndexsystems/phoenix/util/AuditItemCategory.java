package com.ndexsystems.phoenix.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;
import lombok.Data;

/**
 * Implemented manually around 2013/10/04; added in 2014/02/10.
 */
/*
 * (c) Ndex Systems Inc. THIS CLASS HAS BEEN GENERATED. DO NOT CHANGE IT.
 * GENERATED AT: 2019-07-22 10:34:26
 */
@Data
public final class AuditItemCategory extends EnumerationDataType {
	public final static AuditItemCategory ACCESS2FA = new AuditItemCategory("access2FA", "AC2FA", "AC2FA", "AC2FA",
			null);
	public final static AuditItemCategory ACCESSDENIED = new AuditItemCategory("accessDenied", "0", "0", "0", null);
	public final static AuditItemCategory ACCESSDENIEDFIRMUSER = new AuditItemCategory("accessDeniedFirmUser", "ACSDF",
			"ACSDF", "ACSDF", null);
	public final static AuditItemCategory ACCESSDENIEDONLINEUSER = new AuditItemCategory("accessDeniedOnlineUser",
			"ACSDO", "ACSDO", "ACSDO", null);
	public final static AuditItemCategory ACCESSFIRMUSER = new AuditItemCategory("accessFirmUser", "ACSF", "ACSF",
			"ACSF", null);
	public final static AuditItemCategory ACCESSONLINEUSER = new AuditItemCategory("accessOnlineUser", "ACSO", "ACSO",
			"ACSO", null);
	public final static AuditItemCategory ACCOUNTBALANCESETTOZERO = new AuditItemCategory("accountBalanceSetToZero",
			"BAL0", "BAL0", "BAL0", null);
	public final static AuditItemCategory ACCOUNTDELETION = new AuditItemCategory("accountDeletion", "ACDEL", "ACDEL",
			"ACDEL", null);
	public final static AuditItemCategory ACCOUNTINFORMATION = new AuditItemCategory("accountInformation", "ACINF",
			"ACINF", "ACINF", null);
	public final static AuditItemCategory ACCOUNTING = new AuditItemCategory("accounting", "ACTNG", "ACTNG", "ACTNG",
			null);
	public final static AuditItemCategory ACCOUNTUNDELETION = new AuditItemCategory("accountUndeletion", "ACUND",
			"ACUND", "ACUND", null);
	public final static AuditItemCategory ACTIVITYEDITION = new AuditItemCategory("activityEdition", "ACTED", "ACTED",
			"ACTED", null);
	public final static AuditItemCategory ACTIVITYPROCESSDATEEDITION = new AuditItemCategory(
			"activityProcessDateEdition", "APDED", "APDED", "APDED", null);
	public final static AuditItemCategory ADDACTIVITY = new AuditItemCategory("addActivity", "ADACT", "ADACT", "ADACT",
			null);
	public final static AuditItemCategory ASSETSUBCLASS = new AuditItemCategory("assetSubClass", "ASSSC", "ASSSC",
			"ASSSC", null);
	public final static AuditItemCategory BACKDATINGBKSACTIVITY = new AuditItemCategory("backdatingBksActivity",
			"BKACT", "BKACT", "BKACT", null);
	public final static AuditItemCategory BACKDATINGNONBKSACCOUNTCREATIONDATE = new AuditItemCategory(
			"backdatingNonBksAccountCreationDate", "BKACC", "BKACC", "BKACC", null);
	public final static AuditItemCategory BACKDATINGOFNONBKSACTIVITY = new AuditItemCategory(
			"backdatingOfNonBksActivity", "BACKD", "BACKD", "BACKD", null);
	public final static AuditItemCategory BATCHJOBRECURRENCE = new AuditItemCategory("batchJobRecurrence", "BJREC",
			"BJREC", "BJREC", "BJREC");
	public final static AuditItemCategory BATCHOFFBOOKACTIVITY = new AuditItemCategory("batchOffbookActivity", "BOFAC",
			"BOFAC", "BOFAC", null);
	public final static AuditItemCategory BATCHREPORTEXPORT = new AuditItemCategory("batchReportExport", "BATJB",
			"BATJB", "BATJB", null);
	public final static AuditItemCategory BKSACCOUNTINCEPTIONDATEEDITION = new AuditItemCategory(
			"bksAccountInceptionDateEdition", "INCED", "INCED", "INCED", null);
	public final static AuditItemCategory BRANDINGEDITION = new AuditItemCategory("brandingEdition", "BRNED", "BRNED",
			"BRNED", null);
	public final static AuditItemCategory CANCELLATIONMATCHING = new AuditItemCategory("cancellationMatching", "CXMAT",
			"CXMAT", null, null);
	public final static AuditItemCategory CANCELLATIONUNMATCHING = new AuditItemCategory("cancellationUnmatching",
			"CXUNM", "CXUNM", null, null);
	public final static AuditItemCategory CCMFILEUPLOAD = new AuditItemCategory("ccmFileUpload", "CCM", null, null,
			null);
	public final static AuditItemCategory CHANGEOFREPRESENTATIVEFORACCOUNT = new AuditItemCategory(
			"changeOfRepresentativeForAccount", "REPCH", "REPCH", "REPCH", "REPCH");
	public final static AuditItemCategory CLONECUSTOMCRM = new AuditItemCategory("cloneCustomCrm", "CCCRM", "CCCRM",
			"CCCRM", null);
	public final static AuditItemCategory CONTACT = new AuditItemCategory("contact", "CNTCT", null, null, null);
	public final static AuditItemCategory CORPORATEACTION = new AuditItemCategory("corporateAction", "CORP", "CORP",
			null, null);
	public final static AuditItemCategory COSTBASISEDITION = new AuditItemCategory("costBasisEdition", "CBED", null,
			"CBED", null);
	public final static AuditItemCategory CLASSIFIEDMANUALLY = new AuditItemCategory("classifiedManually", "CLMAN",
			null, null, null);
	public final static AuditItemCategory DOCUMENT = new AuditItemCategory("document", "DOC", null, null, null);
	public final static AuditItemCategory DESTINATIONSERVER = new AuditItemCategory("destinationServer", "DSERV",
			"DSERV", null, null);

	public final static AuditItemCategory EDITLOG = new AuditItemCategory("editLog", "14", null, null, null);
	public final static AuditItemCategory EDITCUSTOMCRMANSWER = new AuditItemCategory("editCustomCrmAnswer", "ECRMA",
			null, null, null);
	public final static AuditItemCategory EXTRACTION = new AuditItemCategory("extraction", "XTRCT", "XTRCT", "XTRCT",
			null);
	public final static AuditItemCategory FEEBILLRECALCULATION = new AuditItemCategory("feeBillRecalculation", "FEBIR",
			"FEBIR", null, "FEBIR");
	public final static AuditItemCategory FEEEXCLUSIONLIST = new AuditItemCategory("feeExclusionList", "FEEXL", "FEEXL",
			"FEEXL", null);
	public final static AuditItemCategory FIRMPROPERTIESEDITION = new AuditItemCategory("firmPropertiesEdition",
			"FRMPR", "FRMPR", "FRMPR", null);
	public final static AuditItemCategory FLATFEEPLANMODIFICATION = new AuditItemCategory("flatFeePlanModification",
			"10", "10", "10", "10");
	public final static AuditItemCategory FLATFEEPRODUCT = new AuditItemCategory("flatFeeProduct", "FEEPR", "FEEPR",
			"FEEPR", "FEEPR");
	public final static AuditItemCategory HISTORICALPOSITIONBOOKVALUEEDITION = new AuditItemCategory(
			"historicalPositionBookValueEdition", "HBVE2", "HBVE2", null, null);
	public final static AuditItemCategory HOUSEHOLDACCESS = new AuditItemCategory("householdAccess", "HHACS", "HHACS",
			"HHACS", null);
	public final static AuditItemCategory HOUSEHOLDEDITION = new AuditItemCategory("householdEdition", "HHED", "HHED",
			"HHED", null);
	public final static AuditItemCategory INCOMEATINCEPTIONEDITION = new AuditItemCategory("incomeAtInceptionEdition",
			"IAIED", "IAIED", "IAIED", null);
	public final static AuditItemCategory INTERNALUSE = new AuditItemCategory("internalUse", "99", null, null, null);
	public final static AuditItemCategory INVESTEDCAPITALEDIT = new AuditItemCategory("investedCapitalEdit", "ICEDT",
			"ICEDT", null, null);
	public final static AuditItemCategory INVESTMENTGUIDE = new AuditItemCategory("investmentGuide", "IG", "IG", "IG",
			null);
	public final static AuditItemCategory INVESTORMODIFICATION = new AuditItemCategory("investorModification", "4", "4",
			"4", null);
	public final static AuditItemCategory INVESTORPROFILEEDITION = new AuditItemCategory("investorProfileEdition",
			"INVPR", "INVPR", "INVPR", null);
	public final static AuditItemCategory JOURNALENTRY = new AuditItemCategory("journalEntry", "JE", "JE", "JE", null);
	public final static AuditItemCategory KYC = new AuditItemCategory("kyc", "6", "6", "6", null);
	public final static AuditItemCategory MERGINGLINKINGSECURITY = new AuditItemCategory("mergingLinkingSecurity",
			"MLSEC", "MLSEC", "MLSEC", null);
	public final static AuditItemCategory MISMATCH = new AuditItemCategory("mismatch", "22", null, null, null);
	public final static AuditItemCategory MONTHENDSECURITYPRICEEDITION = new AuditItemCategory(
			"monthEndSecurityPriceEdition", "MESPE", "MESPE", "MESPE", "MESPE");
	public final static AuditItemCategory MONTHLYHISTORYDELETION = new AuditItemCategory("monthlyHistoryDeletion",
			"MHDEL", "MHDEL", null, null);
	public final static AuditItemCategory MONTHLYHISTORYEDITION = new AuditItemCategory("monthlyHistoryEdition", "MHED",
			"MHED", "MHED", null);
	public final static AuditItemCategory MULTIBKSSECURITYMATCHING = new AuditItemCategory("multiBksSecurityMatching",
			"SECMA", null, null, null);
	public final static AuditItemCategory NOTECOMMENT = new AuditItemCategory("noteComment", "COMNT", "COMNT", "COMNT",
			null);
	public final static AuditItemCategory OFFBOOKACCOUNTMERGE = new AuditItemCategory("offbookAccountMerge", "OAMRG",
			"OAMRG", "OAMRG", null);
	public final static AuditItemCategory OFFBOOKINVESTOR = new AuditItemCategory("offbookInvestor", "OBINV", "OBINV",
			"OBINV", null);
	public final static AuditItemCategory OFFBOOKSECURITYEDITION = new AuditItemCategory("offBookSecurityEdition",
			"OBSED", "OBSED", "OBSED", "OBSED");
	public final static AuditItemCategory ONBOOKSECURITYEDITION = new AuditItemCategory("onBookSecurityEdition",
			"SECED", "SECED", "SECED", "SECED");
	public final static AuditItemCategory OPERATION = new AuditItemCategory("operation", "13", "13", "13", null);
	public final static AuditItemCategory ORGANIZATIONUNIT = new AuditItemCategory("organizationUnit", "ORGUN", "ORGUN",
			"ORGUN", null);
	public final static AuditItemCategory PEERVEILIMPORT = new AuditItemCategory("peerveilImport", "PERVL", "PERVL",
			"PERVL", null);
	public final static AuditItemCategory POOLEDFUND = new AuditItemCategory("pooledFund", "POOLF", "POOLF", "POOLF",
			null);
	public final static AuditItemCategory PORTFOLIOCREATION = new AuditItemCategory("portfolioCreation", "PFCR", "PFCR",
			"PFCR", null);
	public final static AuditItemCategory PORTFOLIODELETION = new AuditItemCategory("portfolioDeletion", "PFDEL",
			"PFDEL", "PFDEL", "PFDEL");
	public final static AuditItemCategory POSITIONDELETION = new AuditItemCategory("positionDeletion", "POSDL", "POSDL",
			"POSDL", "POSDL");
	public final static AuditItemCategory POSITIONINFORMATION = new AuditItemCategory("positionInformation", "POSIN",
			"POSIN", "POSIN", null);
	public final static AuditItemCategory PROCESS = new AuditItemCategory("process", "2", "2", "2", null);
	public final static AuditItemCategory REALIZEDGAINCANCELLATION = new AuditItemCategory("realizedGainCancellation",
			"RGCXL", "RGCXL", null, null);
	public final static AuditItemCategory REALIZEDGAINEDITION = new AuditItemCategory("realizedGainEdition", "RGEDT",
			"RGEDT", null, null);
	public final static AuditItemCategory REALIZEDGAINONINKINDTRANSFER = new AuditItemCategory(
			"realizedGainOnInKindTransfer", "RGTFR", "RGTFR", null, null);
	public final static AuditItemCategory RECONCILIATION = new AuditItemCategory("reconciliation", "RCNC", "RCNC", null,
			null);
	public final static AuditItemCategory REGULATORYKYCDAILYFEED = new AuditItemCategory("regulatoryKycDailyFeed",
			"KYCDF", "KYCDF", "KYCDF", null);
	public final static AuditItemCategory REMOVEACTIVITY = new AuditItemCategory("removeActivity", "RMACT", "RMACT",
			"RMACT", null);
	public final static AuditItemCategory REPRESENTATIVEDELETION = new AuditItemCategory("representativeDeletion",
			"REPDL", "REPDL", "REPDL", "REPDL");
	public final static AuditItemCategory REQUESTHISTORICALDATA = new AuditItemCategory("requestHistoricalData",
			"RQHIS", "RQHIS", "RQHIS", "RQHIS");
	public final static AuditItemCategory RETURNOFCAPITALALLOCATION = new AuditItemCategory("returnOfCapitalAllocation",
			"ROCAL", "ROCAL", null, null);
	public final static AuditItemCategory ROLEMANAGEMENT = new AuditItemCategory("roleManagement", "ROLE", "ROLE",
			"ROLE", null);
	public final static AuditItemCategory SECURITYCLOSEPRICEEDITION = new AuditItemCategory("securityClosePriceEdition",
			"CLSPE", "CLSPE", "CLSPE", "CLSPE");
	public final static AuditItemCategory SECURITYDETAILEDITION = new AuditItemCategory("securityDetailEdition",
			"POSED", "POSED", "POSED", null);
	public final static AuditItemCategory STATEMENTUPLOAD = new AuditItemCategory("statementUpload", "STMTU", "CLSPE",
			null, null);
	public final static AuditItemCategory STATICLISTMODIFICATION = new AuditItemCategory("staticListModification",
			"STATL", "STATL", "STATL", null);
	public final static AuditItemCategory TASK = new AuditItemCategory("task", "TASK", "TASK", "TASK", null);
	public final static AuditItemCategory TAXLOTEDITION = new AuditItemCategory("taxLotEdition", "TXLED", null, "TXLED",
			null);
	public final static AuditItemCategory TOTALFEEBEFORETAXEDITION = new AuditItemCategory("totalFeeBeforeTaxEdition",
			"EDFEE", "EDFEE", "EDFEE", null);
	public final static AuditItemCategory TRADING = new AuditItemCategory("trading", "12", "12", "12", null);
	public final static AuditItemCategory TRANSFERACCOUNTDATA = new AuditItemCategory("transferAccountData", "ACTFR",
			"ACTFR", null, "ACTFR");
	public final static AuditItemCategory TRANSFERBOOKVALUE = new AuditItemCategory("transferBookValue", "BVTFR",
			"BVTFR", null, null);
	public final static AuditItemCategory TIMEKEEPING = new AuditItemCategory("timeKeeping", "TMKPN", "TMKPN", null,
			null);

	public final static AuditItemCategory[] DOMAIN = { ACCESS2FA, ACCESSDENIED, ACCESSDENIEDFIRMUSER,
			ACCESSDENIEDONLINEUSER, ACCESSFIRMUSER, ACCESSONLINEUSER, ACCOUNTBALANCESETTOZERO, ACCOUNTINFORMATION,
			ACCOUNTING, ACCOUNTDELETION, ACCOUNTUNDELETION, ACTIVITYEDITION, ACTIVITYPROCESSDATEEDITION, ADDACTIVITY,
			ASSETSUBCLASS, BACKDATINGBKSACTIVITY, BACKDATINGNONBKSACCOUNTCREATIONDATE, BACKDATINGOFNONBKSACTIVITY,
			BATCHJOBRECURRENCE, BATCHOFFBOOKACTIVITY, BATCHREPORTEXPORT, BKSACCOUNTINCEPTIONDATEEDITION,
			BRANDINGEDITION, CANCELLATIONMATCHING, CANCELLATIONUNMATCHING, CCMFILEUPLOAD,
			CHANGEOFREPRESENTATIVEFORACCOUNT, CLONECUSTOMCRM, CONTACT, CORPORATEACTION, COSTBASISEDITION,
			CLASSIFIEDMANUALLY, DOCUMENT, DESTINATIONSERVER, EDITCUSTOMCRMANSWER, EXTRACTION, EDITLOG,
			FEEBILLRECALCULATION, FEEEXCLUSIONLIST, FIRMPROPERTIESEDITION, FLATFEEPLANMODIFICATION, FLATFEEPRODUCT,
			HISTORICALPOSITIONBOOKVALUEEDITION, HOUSEHOLDACCESS, HOUSEHOLDEDITION, INCOMEATINCEPTIONEDITION,
			INTERNALUSE, INVESTEDCAPITALEDIT, INVESTMENTGUIDE, INVESTORMODIFICATION, INVESTORPROFILEEDITION,
			JOURNALENTRY, KYC, MERGINGLINKINGSECURITY, MISMATCH, MONTHENDSECURITYPRICEEDITION, MONTHLYHISTORYEDITION,
			MONTHLYHISTORYDELETION, MULTIBKSSECURITYMATCHING, NOTECOMMENT, OFFBOOKACCOUNTMERGE, OFFBOOKINVESTOR,
			OFFBOOKSECURITYEDITION, ONBOOKSECURITYEDITION, OPERATION, ORGANIZATIONUNIT, PEERVEILIMPORT, POOLEDFUND,
			PORTFOLIOCREATION, PORTFOLIODELETION, POSITIONDELETION, POSITIONINFORMATION, PROCESS,
			REALIZEDGAINCANCELLATION, REALIZEDGAINEDITION, REALIZEDGAINONINKINDTRANSFER, RECONCILIATION,
			REGULATORYKYCDAILYFEED, REMOVEACTIVITY, REPRESENTATIVEDELETION, REQUESTHISTORICALDATA,
			RETURNOFCAPITALALLOCATION, ROLEMANAGEMENT, SECURITYCLOSEPRICEEDITION, SECURITYDETAILEDITION,
			STATEMENTUPLOAD, STATICLISTMODIFICATION, TASK, TAXLOTEDITION, TIMEKEEPING, TOTALFEEBEFORETAXEDITION,
			TRADING, TRANSFERACCOUNTDATA, TRANSFERBOOKVALUE };

	public final static AuditItemCategory[] STANDARDDISPLAY_DOMAIN = { ACCESS2FA, ACCESSDENIED, ACCESSDENIEDFIRMUSER,
			ACCESSDENIEDONLINEUSER, ACCESSFIRMUSER, ACCESSONLINEUSER, ACCOUNTBALANCESETTOZERO, ACCOUNTINFORMATION,
			ACCOUNTING, ACCOUNTDELETION, ACCOUNTUNDELETION, ACTIVITYEDITION, ACTIVITYPROCESSDATEEDITION, ADDACTIVITY,
			ASSETSUBCLASS, BACKDATINGBKSACTIVITY, BACKDATINGNONBKSACCOUNTCREATIONDATE, BACKDATINGOFNONBKSACTIVITY,
			BATCHJOBRECURRENCE, BATCHOFFBOOKACTIVITY, BATCHREPORTEXPORT, BKSACCOUNTINCEPTIONDATEEDITION,
			BRANDINGEDITION, CANCELLATIONMATCHING, CANCELLATIONUNMATCHING, CHANGEOFREPRESENTATIVEFORACCOUNT,
			CLONECUSTOMCRM, CONTACT, CORPORATEACTION, CLASSIFIEDMANUALLY, DOCUMENT, DESTINATIONSERVER,
			EDITCUSTOMCRMANSWER, EXTRACTION, FEEBILLRECALCULATION, FEEEXCLUSIONLIST, FIRMPROPERTIESEDITION,
			FLATFEEPLANMODIFICATION, FLATFEEPRODUCT, HISTORICALPOSITIONBOOKVALUEEDITION, HOUSEHOLDACCESS,
			HOUSEHOLDEDITION, INCOMEATINCEPTIONEDITION, INVESTEDCAPITALEDIT, INVESTMENTGUIDE, INVESTORMODIFICATION,
			INVESTORPROFILEEDITION, JOURNALENTRY, KYC, MERGINGLINKINGSECURITY, MONTHENDSECURITYPRICEEDITION,
			MONTHLYHISTORYEDITION, MONTHLYHISTORYDELETION, NOTECOMMENT, OFFBOOKACCOUNTMERGE, OFFBOOKINVESTOR,
			OFFBOOKSECURITYEDITION, ONBOOKSECURITYEDITION, OPERATION, ORGANIZATIONUNIT, PEERVEILIMPORT, POOLEDFUND,
			PORTFOLIOCREATION, PORTFOLIODELETION, POSITIONDELETION, POSITIONINFORMATION, PROCESS, REALIZEDGAINEDITION,
			REALIZEDGAINCANCELLATION, REALIZEDGAINONINKINDTRANSFER, RECONCILIATION, REGULATORYKYCDAILYFEED,
			REMOVEACTIVITY, REPRESENTATIVEDELETION, REQUESTHISTORICALDATA, RETURNOFCAPITALALLOCATION, ROLEMANAGEMENT,
			SECURITYCLOSEPRICEEDITION, SECURITYDETAILEDITION, STATEMENTUPLOAD, STATICLISTMODIFICATION, TIMEKEEPING,
			TASK, TOTALFEEBEFORETAXEDITION, TRADING, TRANSFERACCOUNTDATA, TRANSFERBOOKVALUE };

	public final static AuditItemCategory[] TAXLOT_DOMAIN = { ACCESS2FA, ACCESSDENIED, ACCESSDENIEDFIRMUSER,
			ACCESSDENIEDONLINEUSER, ACCESSFIRMUSER, ACCESSONLINEUSER, ACCOUNTBALANCESETTOZERO, ACCOUNTINFORMATION,
			ACCOUNTDELETION, ACCOUNTUNDELETION, ACTIVITYEDITION, ACTIVITYPROCESSDATEEDITION, ADDACTIVITY, ASSETSUBCLASS,
			BACKDATINGBKSACTIVITY, BACKDATINGNONBKSACCOUNTCREATIONDATE, BACKDATINGOFNONBKSACTIVITY, BATCHJOBRECURRENCE,
			BATCHOFFBOOKACTIVITY, BATCHREPORTEXPORT, BKSACCOUNTINCEPTIONDATEEDITION, BRANDINGEDITION,
			CANCELLATIONMATCHING, CANCELLATIONUNMATCHING, CHANGEOFREPRESENTATIVEFORACCOUNT, CLONECUSTOMCRM, CONTACT,
			COSTBASISEDITION, CLASSIFIEDMANUALLY, DOCUMENT, DESTINATIONSERVER, EDITCUSTOMCRMANSWER, EXTRACTION,
			FEEEXCLUSIONLIST, FIRMPROPERTIESEDITION, FLATFEEPLANMODIFICATION, FLATFEEPRODUCT, HOUSEHOLDACCESS,
			HOUSEHOLDEDITION, INCOMEATINCEPTIONEDITION, INVESTMENTGUIDE, INVESTORMODIFICATION, INVESTORMODIFICATION,
			JOURNALENTRY, KYC, MERGINGLINKINGSECURITY, MONTHENDSECURITYPRICEEDITION, MONTHLYHISTORYEDITION, NOTECOMMENT,
			OFFBOOKACCOUNTMERGE, OFFBOOKINVESTOR, OFFBOOKSECURITYEDITION, ONBOOKSECURITYEDITION, OPERATION,
			ORGANIZATIONUNIT, PEERVEILIMPORT, POOLEDFUND, PORTFOLIOCREATION, PORTFOLIODELETION, POSITIONDELETION,
			POSITIONINFORMATION, PROCESS, RECONCILIATION, REGULATORYKYCDAILYFEED, REALIZEDGAINEDITION,
			REALIZEDGAINCANCELLATION, REMOVEACTIVITY, REPRESENTATIVEDELETION, ROLEMANAGEMENT, SECURITYCLOSEPRICEEDITION,
			SECURITYDETAILEDITION, STATICLISTMODIFICATION, TASK, TAXLOTEDITION, TIMEKEEPING, TOTALFEEBEFORETAXEDITION,
			TRADING };

	public final static AuditItemCategory[] EMAILNOTIFICATION_DOMAIN = { BATCHJOBRECURRENCE,
			CHANGEOFREPRESENTATIVEFORACCOUNT, FEEBILLRECALCULATION, FLATFEEPLANMODIFICATION, FLATFEEPRODUCT,
			MONTHENDSECURITYPRICEEDITION, ONBOOKSECURITYEDITION, PORTFOLIODELETION, REPRESENTATIVEDELETION,
			SECURITYCLOSEPRICEEDITION, TRANSFERACCOUNTDATA };

	private String standarddisplayRepresentation;
	private String taxlotRepresentation;
	private String emailnotificationRepresentation;
	private String descriptionKey;
	/*
	 * (c) Ndex Systems Inc. THIS METHOD HAS BEEN GENERATED. DO NOT CHANGE IT.
	 * GENERATED AT: 2019-07-22
	 */
	
	private static final Map<String, AuditItemCategory> BY_PERSISTENT_ID = Arrays.stream(DOMAIN)
			.collect(Collectors.toMap(AuditItemCategory::getPersistentId, c -> c));

	private AuditItemCategory(String aName, String aPersistentId, String aStandarddisplayRepresentation,
			String aTaxlotRepresentation, String anEmailnotificationRepresentation) {
		super(aName, aPersistentId);
		standarddisplayRepresentation = aStandarddisplayRepresentation;
		taxlotRepresentation = aTaxlotRepresentation;
		emailnotificationRepresentation = anEmailnotificationRepresentation;
		descriptionKey = "audititemcategory." + getName() + ".description";
	}
	
	public static AuditItemCategory getElementFromPersistentId(String aPersistentId)
	        throws NoSuchElementException {

	    AuditItemCategory category = BY_PERSISTENT_ID.get(aPersistentId);

	    if (category == null) {
	        throw new NoSuchElementException(
	            aPersistentId + " is not a valid persistentId for AuditItemCategory"
	        );
	    }

	    return category;
	}
}



