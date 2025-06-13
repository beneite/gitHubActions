## ✅ Workflow Overview

### 🔧 **Purpose**:

This workflow is designed to be **manually triggered** using `workflow_dispatch`, with **custom input parameters**.

### 📄 **Input Fields**:

* `userName`: A required string input (default = "Ashish Mishra")
* `number`: A number input (default = 4)
* `option`: A required dropdown with choices
* `boolean`: An optional boolean (true/false) flag

### 🧪 **What It Does**:

It logs:

* The current git ref (branch)
* All the user-provided inputs

---

## ⚠️ Issue: It does **not yet restrict to `manualTriggerBranch`**

To restrict this workflow to **run only when triggered on the `manualTriggerBranch`**, you need to add a **conditional check** using `if:` at the job level.

---

### ✅ Fixed Version (Restrict to `manualTriggerBranch`)

```yaml
# .github/workflows/manual-dispatch-branch-specific.yml

name: Manually Triggered with Parameters (only on manualTriggerBranch)

on:
  workflow_dispatch:
    inputs:
      userName:
        description: enter the username of the person who will trigger the workflow
        type: string
        required: true
        default: "Ashish Mishra"
      number:
        description: A number input
        default: 4
        type: number
      option:
        description: "An Options Input"
        required: true
        default: "Option 3"
        type: choice
        options:
          - Option 1
          - Option 2
          - Option 3
      boolean:
        description: A boolean option
        required: false
        type: boolean

jobs:
  log-info:
    runs-on: ubuntu-latest
    steps:
      - run: echo "Running on: $GITHUB_REF"
      - run: |
          echo "String: ${{ inputs.userName }}"
          echo "Number: ${{ inputs.number }}"
          echo "Option: ${{ inputs.option }}"
          echo "Boolean: ${{ inputs.boolean }}"
```

---
### ✅ Test It:

1. Go to **Actions** tab in GitHub.
2. Select this workflow.
3. Choose branch `manualTriggerBranch` from the dropdown.
4. Provide input values.
5. Click **Run workflow**.
6. ✅ The workflow will execute only if the correct branch is selected.

---