# Expected advisory inventory

This file makes the demo more defensible by documenting why each intentionally old dependency exists. GitHub Advisory Database mappings change over time, so treat this as a presentation guide rather than an exact alert contract.

## Expected alert mix

| Ecosystem | Service path | Direct vulnerable pins | Demo expectation |
| --- | --- | ---: | --- |
| Maven | `services/reporting-java` | 10 | Critical/high/medium Java library alerts, including Log4Shell-era coverage. |
| npm | `services/api-node` | 8 | High/medium npm alerts for web framework, utility, JWT, and parsing packages. |
| npm / TypeScript | `services/cli-typescript` | 10 | High/medium npm alerts plus TypeScript-package version-update noise. |
| pip | `services/worker-python` | 8 | High/medium Python web, crypto, YAML, HTTP, and image-library alerts. |
| vcpkg | `services/native-c` | 6 | Version-update coverage for native libraries; security alert behavior may be less complete than npm/pip/Maven. |
| Docker | `docker` | 1 base image | Base-image version update PRs; container vulnerability scanning is a separate GitHub feature. |
| GitHub Actions | `.github/workflows` | 1 action | CI/CD dependency version update PRs. |

Expect roughly 30+ Dependabot alerts after GitHub indexes the repo. The final number can differ because transitive dependencies, withdrawn advisories, and ecosystem support evolve.

## Representative advisories to discuss live

| Ecosystem | Package | Pinned version | Why it is included |
| --- | --- | --- | --- |
| Maven | `org.apache.logging.log4j:log4j-core` | `2.14.1` | Critical Log4Shell-class finding for an easy executive-level story. |
| Maven | `com.fasterxml.jackson.core:jackson-databind` | `2.9.9.3` | Common enterprise Java JSON library with a long advisory history. |
| Maven | `org.yaml:snakeyaml` | `1.26` | YAML parsing risks that map well to supply-chain conversations. |
| npm | `lodash` | `4.17.20` | Familiar utility package with prototype-pollution history. |
| npm | `jsonwebtoken` | `8.5.1` | Security-sensitive auth package that makes remediation impact obvious. |
| npm | `minimist` | `0.0.8` | Small transitive-style package useful for explaining reachability and dependency depth. |
| pip | `PyYAML` | `5.3.1` | Familiar unsafe-deserialization/YAML parsing discussion point. |
| pip | `cryptography` | `3.3.1` | High-signal package for security teams because it handles crypto primitives. |
| pip | `Pillow` | `8.1.0` | Image-processing libraries commonly produce memory-safety and parsing alerts. |
| vcpkg | `openssl` | baseline-pinned | Native crypto library included for C/C++ ecosystem breadth. |
| vcpkg | `curl` | baseline-pinned | Common native network library for version-update and native-dependency discussion. |
| Docker | `node` base image | `16.13.0-alpine` | Demonstrates base image update PRs separately from application dependency PRs. |

## Lockfile note

For the most predictable npm alert counts, generate and commit `package-lock.json` files for `services/api-node` and `services/cli-typescript` from a machine with registry access:

```bash
cd services/api-node && npm install --package-lock-only --ignore-scripts
cd ../cli-typescript && npm install --package-lock-only --ignore-scripts
```

This environment could not fetch npm packages through the configured registry/proxy policy, so the lockfiles should be generated in the GitHub-connected demo environment or by the presenter before the live session.
