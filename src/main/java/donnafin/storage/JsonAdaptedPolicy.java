//@@author sheehui
package donnafin.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import donnafin.commons.exceptions.IllegalValueException;
import donnafin.model.person.Policy;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {

    public final String policyTotalValueInsured;
    public final String policyYearlyPremiums;
    public final String policyCommission;
    private final String policyName;
    private final String policyInsurer;


    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given {@code policyName}.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("name") String policyName, @JsonProperty("insurer") String policyInsurer,
                             @JsonProperty("totalValueInsured")String policyTotalValueInsured,
                             @JsonProperty("yearlyPremiums") String policyYearlyPremiums,
                             @JsonProperty("commission") String policyCommission) {
        this.policyName = policyName;
        this.policyCommission = policyCommission;
        this.policyInsurer = policyInsurer;
        this.policyYearlyPremiums = policyYearlyPremiums;
        this.policyTotalValueInsured = policyTotalValueInsured;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyName = source.getName();
        policyInsurer = source.getInsurer();
        policyYearlyPremiums = source.getYearlyPremiums().toString();
        policyTotalValueInsured = source.getTotalValueInsured().toString();
        policyCommission = source.getCommission().toString();
    }

    @JsonProperty("name")
    public String getPolicyName() {
        return policyName;
    }

    @JsonProperty("commission")
    public String getPolicyCommission() {
        return policyCommission;
    }

    @JsonProperty("insurer")
    public String getPolicyInsurer() {
        return policyInsurer;
    }

    @JsonProperty("totalValueInsured")
    public String getPolicyTotalValueInsured() {
        return policyTotalValueInsured;
    }

    @JsonProperty("yearlyPremiums")
    public String getPolicyYearlyPremiums() {
        return policyYearlyPremiums;
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted policy.
     */
    public Policy toModelType() throws IllegalValueException {
        try {
            return new Policy(
                    policyName, policyInsurer, policyTotalValueInsured, policyYearlyPremiums, policyCommission);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
