---
name: issue-developer
description: Implement GitHub issues with strong scope control and test-first discipline. Use when Codex receives an issue or issue-like task and needs to understand the project, clarify ambiguous requirements, avoid assumptions, implement through small TDD iterations, keep README and Postman assets aligned when needed, defer integration tests until the end, and escalate newly discovered follow-up work to $gh-issue-manager for documentation and issue creation.
---

# Issue Developer

Treat an issue as a bounded engineering contract. Understand the requested outcome, confirm what is unknown, implement in small verified chunks, and surface newly discovered work instead of quietly absorbing it.

## Operating Rules

- Start from the issue scope, not from preferred implementation ideas.
- Ask for clarification whenever the issue, acceptance criteria, or expected behavior are ambiguous enough to change the implementation.
- Avoid assumptions about business rules, API contracts, schema intent, or backward compatibility.
- Split the issue into small work items that can be implemented and verified independently.
- Prefer unit-test-first iterations during implementation.
- Leave integration-style tests for the end unless the user explicitly asks for a different order.
- Check whether README updates are required whenever the user-facing contract, setup flow, or operational behavior changes.
- Check whether Postman assets should be updated whenever API requests, parameters, paths, or response expectations change.
- If new work is discovered that should not be folded into the current task, ask `$gh-issue-manager` to document it and create or update the relevant GitHub issue.

## Workflow

### 1. Understand the Task

When the issue is provided:

1. Restate the target behavior in concrete terms.
2. Extract explicit scope, constraints, and acceptance criteria.
3. Separate facts from inferences.
4. Identify missing decisions or conflicting signals.
5. Break the work into the smallest sensible implementation chunks.

Ask clarifying questions before editing code when any of these are unclear:

- expected external behavior
- allowed tradeoffs
- migration strategy
- compatibility requirements
- success criteria

If the user does not answer and the ambiguity is material, stop and ask. Do not guess.

### 2. Build Project Context

Inspect the relevant code paths before proposing changes:

- entrypoints and user-facing contracts
- service or domain logic
- persistence and configuration
- existing tests and test style
- README or other repository docs that may need updates
- Postman assets or other API examples that may need updates

Understand the current architecture and follow existing patterns unless the issue explicitly requires changing them.

### 3. Implement by Small TDD Iterations

For each plan item, use this sequence:

1. choose one small behavior slice
2. add or update a focused unit test for that slice
3. run the focused test and confirm it fails for the right reason
4. implement the smallest production change that makes it pass
5. rerun the focused test
6. confirm the slice is complete before moving to the next plan item

Do not batch multiple large behaviors into one iteration when they can be split.

If unit-test-first is temporarily blocked by missing harness or framework support, explain the blocker and get as close as possible by creating the test shape first.

### 4. Defer Integration Tests Until the End

Once the small unit-test-driven chunks are complete:

1. add or update the integration-style tests needed to prove the full workflow
2. run the targeted integration tests
3. run the broader relevant test suite
4. refactor only while all relevant tests stay green

Use integration tests to confirm the assembled behavior, not as the first line of implementation unless the issue inherently requires end-to-end behavior to be tested first.

### 5. Keep Scope Controlled

Keep current-issue work separate from newly discovered problems.

Examples of follow-up work that should usually become separate issues:

- unrelated bugs found while tracing the issue
- larger refactors required only for code health
- infrastructure or migration work beyond the current acceptance criteria
- documentation gaps not required to complete the change
- broader API cleanup revealed while fixing one endpoint

When this happens:

- note the finding clearly
- do not silently expand the current change unless the user approves
- ask `$gh-issue-manager` to draft or create the follow-up issue

### 6. Update Supporting Artifacts When Needed

Before closing implementation work, check whether supporting artifacts must change.

Update README when:

- setup steps changed
- runtime behavior changed in a user-visible way
- configuration changed
- new developer workflows were introduced

Update Postman assets when:

- request paths changed
- query parameters changed
- request body shape changed
- example usage would otherwise become stale

If no update is needed, say so explicitly in the final summary.

### 7. Validate Before Closeout

Before declaring the issue done:

- confirm the implementation matches the issue scope
- confirm each plan item was verified before moving on
- confirm unit tests cover the main behavior changes
- confirm integration tests were added or updated at the end when appropriate
- confirm README and Postman were reviewed and updated if necessary
- note any residual risks or follow-up items
- distinguish what was verified from what remains assumed

## Clarification Triggers

Ask the user instead of assuming when you encounter:

- vague words like "support", "improve", or "fix" without clear expected behavior
- a response contract change that could break consumers
- competing implementation directions with materially different tradeoffs
- migration steps that could affect data or deployment
- missing acceptance criteria for a user-visible feature

## Issue Escalation Rule

If you find a legitimate new issue while implementing:

1. summarize the problem, impact, and why it is out of scope
2. invoke `$gh-issue-manager` to document it
3. create or update the GitHub issue before forgetting the context, when repository permissions allow
4. continue only on the original issue unless the user redirects scope

Read [references/handoff-patterns.md](references/handoff-patterns.md) when you need wording for clarification requests, iteration checkpoints, or follow-up issue escalation.

## Deliverable Shapes

Typical outputs from this skill:

- clarification questions before implementation
- a small-chunk implementation plan tied to the issue
- unit-test-first iterations with explicit checkpoints
- integration tests added near the end
- concise summary of completed work, tests run, README/Postman updates, and remaining risks
- handoff to `$gh-issue-manager` for discovered follow-up issues
