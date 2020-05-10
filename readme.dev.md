# Alifba Eksmo Parser

## How to run

**Common flags:**
* `step`: Current step of the parser.
* `input-dir`: Dir name with input XMLs. Default: "alifba-input".
* `temporary-dir`: Dir name with model cache. Default: "alifba-temporary".

### Step 1: Download XMLs to input dir

**Flags:**
* `method`: API method to use: ["products", "products_full"].
* `count`: Entities per XML file count.

**Dev config:**
`./gradlew bootRun -Pargs="--step=download,--input-dir=alifba-input-dev,--method=products,--count=50"`

**Prod config:**
`./gradlew bootRun -Pargs="--step=download,--input-dir=alifba-input-prod,--method=products_full,--count=1000"`

### Step 2: Calculate statistics (Optional)
`./gradlew bootRun -Pargs="--step=statistics"`

### Step 3: Convert to YML

**Flags:**
* `output-dir`: Dir name with output. Default: "alifba-output".

`./gradlew bootRun -Pargs="--step=convert"`
