# ✅ **Workflow Name**

```yaml
name: Maven Cache Check with manual caching
```

This is just a label for your workflow when it appears in the GitHub Actions UI.

---

## 🚀 **Trigger**

```yaml
on:
  workflow_dispatch:
```

This allows you to **manually trigger the workflow** from the GitHub UI.

---

## 🧪 **Job: `cache-test`**

```yaml
jobs:
  cache-test:
    runs-on: ubuntu-latest
```

This job runs on the **Ubuntu runner**.

---

### 🔹 **Step 1: Checkout Code**

```yaml
- name: Step 1 - Checkout Code
  uses: actions/checkout@v4
```

Pulls the latest code from the repository. This is necessary if your `pom.xml` is inside the repo and needed for hashing.

---

### 🔹 **Step 2: Cache Maven dependencies**

```yaml
- name: Step 2 - Cache Maven dependencies
  id: maven-cache
  uses: actions/cache@v3
  with:
    path: ~/.m2/repository
    key: maven-${{ runner.os }}-${{ hashFiles('**/pom.xml') }}
```

* `path`: Tells GitHub where the Maven dependencies are stored (default `.m2/repository`)
* `key`: A unique key generated based on the OS and a hash of all `pom.xml` files.

    * If any `pom.xml` changes, the hash changes → **new cache is created**.
* `id: maven-cache` is used to refer to this step later.

---

### 🔹 **Step 3: Show Cache Status**

```yaml
- name: Step 4 - Show Cache Status
  run: echo '${{ steps.maven-cache.outputs }}'
```

Prints the metadata for the cache step. You can change it to:

```yaml
run: echo "Cache hit: ${{ steps.maven-cache.outputs.cache-hit }}"
```

To **explicitly see if the cache was hit or not**.

---

### 🔹 **Step 4: Setup Java**

```yaml
- name: Step 4 - Setup Java
  uses: actions/setup-java@v3
  with:
    distribution: 'temurin'
    java-version: '17'
```

Sets up Java 17 (Temurin distribution). This is required for Maven to run.

---

### 🔹 **Step 5: Preload Maven Dependencies**

```yaml
- name: Step 5 - Run a dummy Maven goal (skip tests) to fetch all the dependencies
  run: mvn dependency:go-offline -B
```

Runs Maven in *offline preparation mode* — it downloads all the dependencies needed for your project without actually running tests.

* `-B` makes it run in **batch (non-interactive)** mode
* This confirms whether Maven is using already cached `.m2` files or redownloading

---

### 🔹 **Step 6: Completion Log**

```yaml
- name: Step 5 - Done
  run: echo "📦 Dependency download completed"
```

Just a friendly message for visual confirmation in the GitHub Actions log.

---

## 🔁 Summary Flow

1. On manual trigger, code is checked out.
2. Maven `.m2/repository` is restored from cache (if exists).
3. `dependency:go-offline` runs — this is where you’ll see if dependencies were downloaded again or used from cache.
4. Workflow completes.

---

Let me know if you want to **add logging to show exact Maven behavior**, or if you'd like to test it **with cache intentionally disabled** for comparison.
