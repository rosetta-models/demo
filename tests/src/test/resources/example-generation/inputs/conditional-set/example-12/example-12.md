Example 12:
Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path with multi-cardinality.
The attribute EngineSpecification->capacityUnit is conditionally set from xml path engine->engineType->engineDetail->
volumeCapacityUnit when the value at xml path engineType->engineDetail->combustible = "Petrol" and attribute
EngineSpecification->fuelType is conditionally set from xml path engine->engineType->engineDetail->combustible when the
value at xml path engineType->engineDetail->combustible = "Petrol".