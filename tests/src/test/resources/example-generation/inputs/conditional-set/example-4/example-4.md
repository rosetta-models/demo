Example 4:

Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path.

The attribute EngineSpecification->capacityUnit is conditionally set from xml path engine->engineType->engineDetail->
volumeCapacityUnit when the value at xml path engine->engineType->engineDetail->isFromUK is True.