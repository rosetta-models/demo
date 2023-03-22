Example 1:

Conditional mapping using a "default" clause that sets an attribute if it is unmapped.

The attribute EngineSpecification->capacityUnit is mapped to xml path engine->engineType->engineDetail->
volumeCapacityUnit, if that path does not exist, then the attribute is set to "UK Gallon".