/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.core.geneticalgorithm.scope;

import org.optaplanner.core.phase.AbstractSolverPhaseScope;
import org.optaplanner.core.phase.step.AbstractStepScope;

public class GeneticAlgorithmStepScope extends AbstractStepScope {

    private final GeneticAlgorithmSolverPhaseScope phaseScope;

    public GeneticAlgorithmStepScope(GeneticAlgorithmSolverPhaseScope phaseScope) {
        this.phaseScope = phaseScope;
    }

    @Override
    public GeneticAlgorithmSolverPhaseScope getPhaseScope() {
        return phaseScope;
    }

    //TODO what is this for?
    @Override
    public boolean isBestSolutionCloningDelayed() {
        return false;
    }

    //TODO what is this for?
    @Override
    public int getUninitializedVariableCount() {
        return 0;
    }
}
