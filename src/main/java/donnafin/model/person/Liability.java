package donnafin.model.person;

import static donnafin.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import donnafin.commons.core.types.Money;
import donnafin.logic.parser.ParserUtil;
import donnafin.logic.parser.exceptions.ParseException;
import donnafin.ui.AttributeTable;

/**
 * Represents a Person's liability in DonnaFin.
 */
public class Liability implements Attribute {

    public static final String MESSAGE_CONSTRAINTS = "Insert liability constraint here";
    public static final AttributeTable.TableConfig<Liability> TABLE_CONFIG = new AttributeTable.TableConfig<>(
        "Liabilities",
        List.of(
                new AttributeTable.ColumnConfig("Liability Name", "name", 300),
                new AttributeTable.ColumnConfig("Type", "type", 100),
                new AttributeTable.ColumnConfig("Value", "valueToString", 100),
                new AttributeTable.ColumnConfig("Remarks", "remarks", 100)
        ),
        liabilityCol -> {
            Money acc = new Money(0);
            try {
                for (Liability liability : liabilityCol) {
                    Money commission = liability.getValue();
                    acc = Money.add(acc, commission);
                }
            } catch (Money.MoneyException e) {
                return "-";
            }
            return "Total Liability Value: " + acc;
        }
    );
    private final String name;
    private final String type;
    private final Money value;
    private final String remarks;

    /**
     * Constructs a {@code Liability}.
     *
     * @param name A valid Liability name.
     * @param type A Liability type.
     * @param value A Liability's worth.
     * @param remarks A remark on Liability.
     */
    public Liability(String name, String type, String value, String remarks) {
        requireAllNonNull(name, type, value, remarks);
        this.name = name;
        this.type = type;
        try {
            this.value = ParserUtil.parseMoney(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException(Policy.MESSAGE_CONSTRAINTS);
        }
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Liability{"
                + "name='" + getName() + '\''
                + ", type='" + getType() + '\''
                + ", value='" + getValue() + '\''
                + ", remarks='" + getRemarks() + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Liability)) {
            return false;
        }

        Liability liability = (Liability) o;
        return getName().equals(liability.getName())
                && getType().equals(liability.getType())
                && getValue().equals(liability.getValue())
                && getRemarks().equals(liability.getRemarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), getValue(), getRemarks());
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Money getValue() {
        return value;
    }

    public String getValueToString() {
        return value.toString();
    }

    public String getRemarks() {
        return remarks;
    }
}
