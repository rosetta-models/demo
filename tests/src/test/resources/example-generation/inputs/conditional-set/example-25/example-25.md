Example 25:
Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path conditioned when it finds 2 conditions for rosetta attribute usEngineVersion and ukEngineSpecification
respectively:
-"fuelSpecs->combustibleref->href" = "US" or "fuelSpecs->combustibleref->href" = "UK".
-"combustible" set when "combustible->id" = "US" or "combustible" set when "combustible->id" = "UK" 