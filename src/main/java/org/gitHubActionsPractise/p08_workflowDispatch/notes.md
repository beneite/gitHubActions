# ✅ **What this workflow does:**

This GitHub Actions workflow named **`workflow_dispatch - Run Test cases`**:

* **Triggers manually** (`workflow_dispatch`) only.
* Accepts **custom inputs** from the user:

    * `userName`: who is running the tests
    * `testPriority`: priority like P1/P2
    * `testType`: type of test suite (smoke, regression, sanity)
    * `purpose`: optional reason for the test run
* Runs **only on the branch `manualTriggerBranch`**
* Checks out the repository, sets up Java & Maven.
* Runs the selected test suite using Maven, based on the `testType` input.
* Logs key steps and provides nice wrapping messages at the end.

---