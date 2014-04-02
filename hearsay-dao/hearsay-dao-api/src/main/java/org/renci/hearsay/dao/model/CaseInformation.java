package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CaseInformation")
@XmlRootElement(name = "caseInformation")
@Entity
@Table(name = "case_information")
public class CaseInformation implements Persistable {

    private static final long serialVersionUID = -6743665416456680462L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_information_id_seq")
    @SequenceGenerator(name = "case_information_id_seq", sequenceName = "case_information_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "case_information_id")
    private Long primaryKey;

    @Column(name = "ethnicity")
    private String ethnicity;

    @Column(name = "race")
    private String race;

    @Column(name = "age_of_onset")
    private String ageOfOnset;

    @Column(name = "sex")
    private String sex;

    @Column(name = "geographic_origin")
    private String geographicOrigin;

    @Column(name = "proband")
    private String proband;

    @Column(name = "family_number")
    private String familyNumber;

    @Column(name = "family_history")
    private String familyHistory;

    @Column(name = "phenotype")
    private String phenotype;

    @Column(name = "case_type")
    private String caseType;

    @Column(name = "test_type")
    private String testType;

    @Column(name = "pre_test_status")
    private String preTestStatus;

    @Column(name = "clarification_of_unaffected_status")
    private String clarificationOfUnaffectedStatus;

    @Column(name = "clinical_features")
    private String clinicalFeatures;

    @Column(name = "family_members_affected")
    private String familyMembersAffected;

    @Column(name = "post_test_clinical_diagnosis")
    private String postTestClinicalDiagnosis;

    @Column(name = "specimen_type")
    private String specimenType;

    @Column(name = "genomic_source")
    private String genomicSource;

    @Column(name = "tissue_source")
    private String tissueSource;

    @Column(name = "tumor_percentage")
    private String tumorPercentage;

    @Column(name = "test_information")
    private String testInformation;

    @Column(name = "test_performed")
    private String testPerformed;

    @Column(name = "interpreted_disease")
    private String interpretedDisease;

    @Column(name = "platform_name")
    private String platformName;

    @Column(name = "platform_type")
    private String platformType;

    @Column(name = "software_name")
    private String softwareName;

    @Column(name = "post_test_molecular_diagnosis")
    private String postTestMolecularDiagnosis;

    @Column(name = "parental_origin")
    private String parentalOrigin;

    @Column(name = "de_novo_variant")
    private String deNovoVariant;

    @Column(name = "mosaicism")
    private String mosaicism;

    @Column(name = "variant_allele_zygosity")
    private String variantAlleleZygosity;

    @Column(name = "clinical_assertion")
    private String clinicalAssertion;

    public CaseInformation() {
        super();
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAgeOfOnset() {
        return ageOfOnset;
    }

    public void setAgeOfOnset(String ageOfOnset) {
        this.ageOfOnset = ageOfOnset;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGeographicOrigin() {
        return geographicOrigin;
    }

    public void setGeographicOrigin(String geographicOrigin) {
        this.geographicOrigin = geographicOrigin;
    }

    public String getProband() {
        return proband;
    }

    public void setProband(String proband) {
        this.proband = proband;
    }

    public String getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(String phenotype) {
        this.phenotype = phenotype;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getPreTestStatus() {
        return preTestStatus;
    }

    public void setPreTestStatus(String preTestStatus) {
        this.preTestStatus = preTestStatus;
    }

    public String getClarificationOfUnaffectedStatus() {
        return clarificationOfUnaffectedStatus;
    }

    public void setClarificationOfUnaffectedStatus(String clarificationOfUnaffectedStatus) {
        this.clarificationOfUnaffectedStatus = clarificationOfUnaffectedStatus;
    }

    public String getClinicalFeatures() {
        return clinicalFeatures;
    }

    public void setClinicalFeatures(String clinicalFeatures) {
        this.clinicalFeatures = clinicalFeatures;
    }

    public String getFamilyMembersAffected() {
        return familyMembersAffected;
    }

    public void setFamilyMembersAffected(String familyMembersAffected) {
        this.familyMembersAffected = familyMembersAffected;
    }

    public String getPostTestClinicalDiagnosis() {
        return postTestClinicalDiagnosis;
    }

    public void setPostTestClinicalDiagnosis(String postTestClinicalDiagnosis) {
        this.postTestClinicalDiagnosis = postTestClinicalDiagnosis;
    }

    public String getSpecimenType() {
        return specimenType;
    }

    public void setSpecimenType(String specimenType) {
        this.specimenType = specimenType;
    }

    public String getGenomicSource() {
        return genomicSource;
    }

    public void setGenomicSource(String genomicSource) {
        this.genomicSource = genomicSource;
    }

    public String getTissueSource() {
        return tissueSource;
    }

    public void setTissueSource(String tissueSource) {
        this.tissueSource = tissueSource;
    }

    public String getTumorPercentage() {
        return tumorPercentage;
    }

    public void setTumorPercentage(String tumorPercentage) {
        this.tumorPercentage = tumorPercentage;
    }

    public String getTestInformation() {
        return testInformation;
    }

    public void setTestInformation(String testInformation) {
        this.testInformation = testInformation;
    }

    public String getTestPerformed() {
        return testPerformed;
    }

    public void setTestPerformed(String testPerformed) {
        this.testPerformed = testPerformed;
    }

    public String getInterpretedDisease() {
        return interpretedDisease;
    }

    public void setInterpretedDisease(String interpretedDisease) {
        this.interpretedDisease = interpretedDisease;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getPostTestMolecularDiagnosis() {
        return postTestMolecularDiagnosis;
    }

    public void setPostTestMolecularDiagnosis(String postTestMolecularDiagnosis) {
        this.postTestMolecularDiagnosis = postTestMolecularDiagnosis;
    }

    public String getParentalOrigin() {
        return parentalOrigin;
    }

    public void setParentalOrigin(String parentalOrigin) {
        this.parentalOrigin = parentalOrigin;
    }

    public String getDeNovoVariant() {
        return deNovoVariant;
    }

    public void setDeNovoVariant(String deNovoVariant) {
        this.deNovoVariant = deNovoVariant;
    }

    public String getMosaicism() {
        return mosaicism;
    }

    public void setMosaicism(String mosaicism) {
        this.mosaicism = mosaicism;
    }

    public String getVariantAlleleZygosity() {
        return variantAlleleZygosity;
    }

    public void setVariantAlleleZygosity(String variantAlleleZygosity) {
        this.variantAlleleZygosity = variantAlleleZygosity;
    }

    public String getClinicalAssertion() {
        return clinicalAssertion;
    }

    public void setClinicalAssertion(String clinicalAssertion) {
        this.clinicalAssertion = clinicalAssertion;
    }

    @Override
    public String toString() {
        return String
                .format("CaseInformation [primaryKey=%s, ethnicity=%s, race=%s, ageOfOnset=%s, sex=%s, geographicOrigin=%s, proband=%s, familyNumber=%s, familyHistory=%s, phenotype=%s, caseType=%s, testType=%s, preTestStatus=%s, clarificationOfUnaffectedStatus=%s, clinicalFeatures=%s, familyMembersAffected=%s, postTestClinicalDiagnosis=%s, specimenType=%s, genomicSource=%s, tissueSource=%s, tumorPercentage=%s, testInformation=%s, testPerformed=%s, interpretedDisease=%s, platformName=%s, platformType=%s, softwareName=%s, postTestMolecularDiagnosis=%s, parentalOrigin=%s, deNovoVariant=%s, mosaicism=%s, variantAlleleZygosity=%s, clinicalAssertion=%s]",
                        primaryKey, ethnicity, race, ageOfOnset, sex, geographicOrigin, proband, familyNumber,
                        familyHistory, phenotype, caseType, testType, preTestStatus, clarificationOfUnaffectedStatus,
                        clinicalFeatures, familyMembersAffected, postTestClinicalDiagnosis, specimenType,
                        genomicSource, tissueSource, tumorPercentage, testInformation, testPerformed,
                        interpretedDisease, platformName, platformType, softwareName, postTestMolecularDiagnosis,
                        parentalOrigin, deNovoVariant, mosaicism, variantAlleleZygosity, clinicalAssertion);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ageOfOnset == null) ? 0 : ageOfOnset.hashCode());
        result = prime * result + ((caseType == null) ? 0 : caseType.hashCode());
        result = prime * result
                + ((clarificationOfUnaffectedStatus == null) ? 0 : clarificationOfUnaffectedStatus.hashCode());
        result = prime * result + ((clinicalAssertion == null) ? 0 : clinicalAssertion.hashCode());
        result = prime * result + ((clinicalFeatures == null) ? 0 : clinicalFeatures.hashCode());
        result = prime * result + ((deNovoVariant == null) ? 0 : deNovoVariant.hashCode());
        result = prime * result + ((ethnicity == null) ? 0 : ethnicity.hashCode());
        result = prime * result + ((familyHistory == null) ? 0 : familyHistory.hashCode());
        result = prime * result + ((familyMembersAffected == null) ? 0 : familyMembersAffected.hashCode());
        result = prime * result + ((familyNumber == null) ? 0 : familyNumber.hashCode());
        result = prime * result + ((genomicSource == null) ? 0 : genomicSource.hashCode());
        result = prime * result + ((geographicOrigin == null) ? 0 : geographicOrigin.hashCode());
        result = prime * result + ((interpretedDisease == null) ? 0 : interpretedDisease.hashCode());
        result = prime * result + ((mosaicism == null) ? 0 : mosaicism.hashCode());
        result = prime * result + ((parentalOrigin == null) ? 0 : parentalOrigin.hashCode());
        result = prime * result + ((phenotype == null) ? 0 : phenotype.hashCode());
        result = prime * result + ((platformName == null) ? 0 : platformName.hashCode());
        result = prime * result + ((platformType == null) ? 0 : platformType.hashCode());
        result = prime * result + ((postTestClinicalDiagnosis == null) ? 0 : postTestClinicalDiagnosis.hashCode());
        result = prime * result + ((postTestMolecularDiagnosis == null) ? 0 : postTestMolecularDiagnosis.hashCode());
        result = prime * result + ((preTestStatus == null) ? 0 : preTestStatus.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((proband == null) ? 0 : proband.hashCode());
        result = prime * result + ((race == null) ? 0 : race.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
        result = prime * result + ((softwareName == null) ? 0 : softwareName.hashCode());
        result = prime * result + ((specimenType == null) ? 0 : specimenType.hashCode());
        result = prime * result + ((testInformation == null) ? 0 : testInformation.hashCode());
        result = prime * result + ((testPerformed == null) ? 0 : testPerformed.hashCode());
        result = prime * result + ((testType == null) ? 0 : testType.hashCode());
        result = prime * result + ((tissueSource == null) ? 0 : tissueSource.hashCode());
        result = prime * result + ((tumorPercentage == null) ? 0 : tumorPercentage.hashCode());
        result = prime * result + ((variantAlleleZygosity == null) ? 0 : variantAlleleZygosity.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CaseInformation other = (CaseInformation) obj;
        if (ageOfOnset == null) {
            if (other.ageOfOnset != null)
                return false;
        } else if (!ageOfOnset.equals(other.ageOfOnset))
            return false;
        if (caseType == null) {
            if (other.caseType != null)
                return false;
        } else if (!caseType.equals(other.caseType))
            return false;
        if (clarificationOfUnaffectedStatus == null) {
            if (other.clarificationOfUnaffectedStatus != null)
                return false;
        } else if (!clarificationOfUnaffectedStatus.equals(other.clarificationOfUnaffectedStatus))
            return false;
        if (clinicalAssertion == null) {
            if (other.clinicalAssertion != null)
                return false;
        } else if (!clinicalAssertion.equals(other.clinicalAssertion))
            return false;
        if (clinicalFeatures == null) {
            if (other.clinicalFeatures != null)
                return false;
        } else if (!clinicalFeatures.equals(other.clinicalFeatures))
            return false;
        if (deNovoVariant == null) {
            if (other.deNovoVariant != null)
                return false;
        } else if (!deNovoVariant.equals(other.deNovoVariant))
            return false;
        if (ethnicity == null) {
            if (other.ethnicity != null)
                return false;
        } else if (!ethnicity.equals(other.ethnicity))
            return false;
        if (familyHistory == null) {
            if (other.familyHistory != null)
                return false;
        } else if (!familyHistory.equals(other.familyHistory))
            return false;
        if (familyMembersAffected == null) {
            if (other.familyMembersAffected != null)
                return false;
        } else if (!familyMembersAffected.equals(other.familyMembersAffected))
            return false;
        if (familyNumber == null) {
            if (other.familyNumber != null)
                return false;
        } else if (!familyNumber.equals(other.familyNumber))
            return false;
        if (genomicSource == null) {
            if (other.genomicSource != null)
                return false;
        } else if (!genomicSource.equals(other.genomicSource))
            return false;
        if (geographicOrigin == null) {
            if (other.geographicOrigin != null)
                return false;
        } else if (!geographicOrigin.equals(other.geographicOrigin))
            return false;
        if (interpretedDisease == null) {
            if (other.interpretedDisease != null)
                return false;
        } else if (!interpretedDisease.equals(other.interpretedDisease))
            return false;
        if (mosaicism == null) {
            if (other.mosaicism != null)
                return false;
        } else if (!mosaicism.equals(other.mosaicism))
            return false;
        if (parentalOrigin == null) {
            if (other.parentalOrigin != null)
                return false;
        } else if (!parentalOrigin.equals(other.parentalOrigin))
            return false;
        if (phenotype == null) {
            if (other.phenotype != null)
                return false;
        } else if (!phenotype.equals(other.phenotype))
            return false;
        if (platformName == null) {
            if (other.platformName != null)
                return false;
        } else if (!platformName.equals(other.platformName))
            return false;
        if (platformType == null) {
            if (other.platformType != null)
                return false;
        } else if (!platformType.equals(other.platformType))
            return false;
        if (postTestClinicalDiagnosis == null) {
            if (other.postTestClinicalDiagnosis != null)
                return false;
        } else if (!postTestClinicalDiagnosis.equals(other.postTestClinicalDiagnosis))
            return false;
        if (postTestMolecularDiagnosis == null) {
            if (other.postTestMolecularDiagnosis != null)
                return false;
        } else if (!postTestMolecularDiagnosis.equals(other.postTestMolecularDiagnosis))
            return false;
        if (preTestStatus == null) {
            if (other.preTestStatus != null)
                return false;
        } else if (!preTestStatus.equals(other.preTestStatus))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (proband == null) {
            if (other.proband != null)
                return false;
        } else if (!proband.equals(other.proband))
            return false;
        if (race == null) {
            if (other.race != null)
                return false;
        } else if (!race.equals(other.race))
            return false;
        if (sex == null) {
            if (other.sex != null)
                return false;
        } else if (!sex.equals(other.sex))
            return false;
        if (softwareName == null) {
            if (other.softwareName != null)
                return false;
        } else if (!softwareName.equals(other.softwareName))
            return false;
        if (specimenType == null) {
            if (other.specimenType != null)
                return false;
        } else if (!specimenType.equals(other.specimenType))
            return false;
        if (testInformation == null) {
            if (other.testInformation != null)
                return false;
        } else if (!testInformation.equals(other.testInformation))
            return false;
        if (testPerformed == null) {
            if (other.testPerformed != null)
                return false;
        } else if (!testPerformed.equals(other.testPerformed))
            return false;
        if (testType == null) {
            if (other.testType != null)
                return false;
        } else if (!testType.equals(other.testType))
            return false;
        if (tissueSource == null) {
            if (other.tissueSource != null)
                return false;
        } else if (!tissueSource.equals(other.tissueSource))
            return false;
        if (tumorPercentage == null) {
            if (other.tumorPercentage != null)
                return false;
        } else if (!tumorPercentage.equals(other.tumorPercentage))
            return false;
        if (variantAlleleZygosity == null) {
            if (other.variantAlleleZygosity != null)
                return false;
        } else if (!variantAlleleZygosity.equals(other.variantAlleleZygosity))
            return false;
        return true;
    }

}
