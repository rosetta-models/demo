Example 10:
Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path with multi-cardinality.
The attribute Engine->engineSpecification->engineMetric->fuel is conditionally set from xml path
engineType->engineDetail->combustible in engineMetric type when the value at xml path engineType->engineDetail->
engineCategory = "Combustion".