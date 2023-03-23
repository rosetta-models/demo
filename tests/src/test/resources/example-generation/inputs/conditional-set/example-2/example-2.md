Example 2:

Conditional mapping using "set" with a "when" clause that is predicated on a xml element at the specified xml path
exists.

The attribute EngineSpecification->capacityUnit is conditionally set from xml path engine->engineType->engineDetail->
volumeCapacityUnit when there is a value at xml path engine->engineType->engineDetail->powerUnit.