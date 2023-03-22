Example 3:

Conditional mapping using "set" with a "when" clause that is predicated on whether a xml element at the specified xml
path is absent.

The attribute EngineSpecification->capacityUnit is conditionally set from xml path engine->engineType->engineDetail->
volumeCapacityUnit when there is no value at xml path engine->engineType->engineDetail->powerUnit.