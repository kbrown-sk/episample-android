package org.taskforce.episample.db.filter.date

import org.taskforce.episample.db.config.customfield.CustomField
import org.taskforce.episample.db.config.customfield.value.DateValue
import org.taskforce.episample.db.filter.Rule
import org.taskforce.episample.db.filter.RuleMaker
import java.util.*


class DateRuleFactory {
    enum class DateRules(val displayName: String, val comparator: (Date, Date) -> Boolean) {
        LESS_THAN("<", { lhs: Date, rhs: Date -> lhs < rhs }),
        LESS_THAN_OR_EQUAL_TO("≤", { lhs: Date, rhs: Date -> lhs <= rhs }),
        GREATER_THAN(">", { lhs: Date, rhs: Date -> lhs > rhs }),
        GREATER_THAN_OR_EQUAL_TO("≥", { lhs: Date, rhs: Date -> lhs >= rhs }),
        IS_EQUAL_TO("=", { lhs: Date, rhs: Date -> lhs == rhs }),
        IS_NOT_EQUAL_TO("≠", { lhs: Date, rhs: Date -> lhs != rhs });
    }

    companion object: RuleMaker<DateRules, Date> {
        override fun makeRule(ruleType: DateRules, forField: CustomField, value: Date): Rule {
            return DateComparisonRule(ruleType.comparator, forField, DateValue(value))
        }
    }
}