# Alifba Eksmo Parser

## How to run

**Flags:**
* `step`: Current step of the parser.
* `count`: Entities count per XML file.
* `clean`: Do or don't do catalog optimizations like cleaning unused categories in it.

### Step 1: Download XMLs
`./gradlew bootRun -Pargs="--step=download,--count=50"`

### Step 2: Calculate statistics
`./gradlew bootRun -Pargs="--step=statistics,--clean=true"`

### Step 3: Convert to YML
`./gradlew bootRun -Pargs="--step=convert,--clean=true"`
