# ✅ What This Workflow Does

This GitHub Actions workflow demonstrates **automatic Maven dependency caching** using `actions/setup-java@v3` with the `cache: 'maven'` option. It downloads dependencies using `mvn dependency:go-offline` to test the cache behavior — without running tests.

---

## 🔍 Step-by-Step Breakdown

### \`\`\`yaml

on:
workflow\_dispatch:

````
- This allows **manual triggering** of the workflow through the GitHub Actions UI.
- Great for experiments or validation (like caching checks).

---

### ```yaml
jobs:
  auto-cache-test:
    runs-on: ubuntu-latest
````

* Defines a single job named `auto-cache-test` that runs on the **latest Ubuntu GitHub-hosted runner**.

---

### 🧩 Step 1 – Checkout Code

```yaml
- name: Step 1 - Checkout Code
  uses: actions/checkout@v4
```

* Checks out your repo so the workflow has access to files like `pom.xml`, which is needed to resolve Maven dependencies.

---

### ⚙️ Step 2 – Setup Java and Auto Cache

```yaml
- name: Step 2 - Setup Java with Auto Maven Cache
  uses: actions/setup-java@v3
  with:
    distribution: 'temurin'
    java-version: '17'
    cache: 'maven'
```

* Sets up **Temurin Java 17** (an OpenJDK build).
* `cache: 'maven'` enables **automatic caching of dependencies** in the `.m2/repository` folder:

    * GitHub calculates a **cache key using `pom.xml`** hash.
    * If a cache hit is found → uses that.
    * If not → downloads dependencies, then **saves** them for next run.

> 📌 You don’t need to write manual `actions/cache` logic.

---

### 📦 Step 3 – Cache Info (Message Only)

```yaml
- name: Step 3 - Display Cache Path Used
  run: |
    echo "📦 Maven cache is handled internally via setup-java"
```

* Just an informational message to indicate that caching is in place.
* Doesn’t show actual cache hit/miss — we can add that explicitly if needed.

---

### 🚀 Step 4 – Download Dependencies

```yaml
- name: Step 4 - Run Dummy Maven Goal (go-offline)
  run: mvn dependency:go-offline -B
```

* Runs Maven in **offline preparation mode**:

    * Downloads all dependencies without executing the tests.
    * Useful to verify if dependencies are **pulled from cache** or downloaded anew (check logs).
    * `-B` = batch mode (no interactive prompts).

---

### ✅ Step 5 – Final Message

```yaml
- name: Step 5 - Job Complete
  run: echo "✅ Maven auto-caching check completed"
```

* Final status message to indicate the job completed.

---

## 🔎 How to Confirm Cache Hit/Miss

To **check if cache is used or missed**, open the job logs in GitHub Actions:

* If it's a **hit**, you’ll see:

  ```
  Cache restored successfully
  ```

  and fewer `[INFO] Downloading from central:` logs.

* If it's a **miss**, Maven will download dependencies again.

---
