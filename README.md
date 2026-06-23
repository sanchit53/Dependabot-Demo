# Dependabot Demo: Replacing Veracode SCA

This repository is an intentionally vulnerable, multi-language demo prop for showing how GitHub Dependabot can surface dependency risk and open remediation pull requests. It contains five tiny services:

| Service | Path | Package manager | Health check |
| --- | --- | --- | --- |
| Java | `services/reporting-java` | Maven | `GET /health` on port `8081` |
| Node.js | `services/api-node` | npm | `GET /health` on port `8082` |
| Python | `services/worker-python` | pip | `GET /health` on port `8083` |
| C | `services/native-c` | vcpkg/CMake | prints JSON to stdout |
| TypeScript | `services/cli-typescript` | npm | `GET /health` on port `8085` |

> **Important:** These dependencies are deliberately old and vulnerable. Do not reuse these versions in production code.

## How to enable Dependabot for the live demo

1. Push this repository to GitHub.
2. In GitHub, open **Settings > Code security and analysis**.
3. Enable **Dependency graph**, **Dependabot alerts**, and **Dependabot security updates**.
4. Leave Dependabot PRs as manual review items. This repository intentionally does not include auto-merge, auto-approve, or merge-on-green automation.
5. Confirm `.github/dependabot.yml` is present. Dependabot is configured for Maven, npm, pip, vcpkg, GitHub Actions, and Docker manifests.

## Vulnerability mix to expect

The manifests intentionally pin 40+ old direct dependencies across the five services, with roughly 30+ expected Dependabot alerts once GitHub indexes the repository. The exact alert count can vary because GitHub Advisory Database entries, transitive dependency detection, withdrawn advisories, and ecosystem support change over time. Expect at least one critical advisory, several high-severity advisories, and a broader set of medium/low findings.

Examples include Log4Shell in `log4j-core`, prototype pollution and command-injection issues in npm packages, path traversal issues in Java and Python libraries, and historic OpenSSL/cURL/libxml2/libpng/zlib/sqlite advisories through the C vcpkg baseline.


## Demo reliability notes

- Review routing is demonstrated with `.github/CODEOWNERS`; replace placeholder teams before presenting from a real organization.
- npm alert counts are most predictable when `package-lock.json` files are generated in an environment with npm registry access.
- vcpkg is included for native C dependency breadth, but security alert behavior is less mature than npm, pip, and Maven; call this out during the demo.
- The advisory count may differ slightly from the inventory because GitHub Advisory Database mappings and transitive dependency resolution change over time.
- See `docs/advisory-inventory.md` for the package-by-package presentation guide and `docs/demo-checklist.md` for the live-demo readiness checklist.

## Local smoke commands

```bash
# Java
cd services/reporting-java && mvn -q -DskipTests package

# Node.js / TypeScript require npm registry access to install dependencies
cd services/api-node && npm install && npm start
cd services/cli-typescript && npm install && npm run build && npm start

# Python
cd services/worker-python && python -m venv .venv && . .venv/bin/activate && pip install -r requirements.txt && python app.py

# C with vcpkg toolchain configured
cd services/native-c && cmake -S . -B build -DCMAKE_TOOLCHAIN_FILE=$VCPKG_ROOT/scripts/buildsystems/vcpkg.cmake && cmake --build build && ./build/c-service
```
