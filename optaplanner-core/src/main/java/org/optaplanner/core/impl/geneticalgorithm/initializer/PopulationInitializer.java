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

package org.optaplanner.core.impl.geneticalgorithm.initializer;

import org.optaplanner.core.impl.geneticalgorithm.event.GeneticAlgorithmSolverPhaseLifeCycleListener;
import org.optaplanner.core.impl.geneticalgorithm.scope.GeneticAlgorithmSolverPhaseScope;
import org.optaplanner.core.impl.score.director.ScoreDirector;

//TODO should be in operator package? of
//TODO maybe add abstract class with code which is reused in every type of initializer
public interface PopulationInitializer extends GeneticAlgorithmSolverPhaseLifeCycleListener {

	/**
	 * Initialize a population of solutions. Solutions
	 * should be added to generation in phaseScope. Scores
	 * need not be calculated after generating a new individual
	 * @param phaseScope
	 */
	void initializePopulation(GeneticAlgorithmSolverPhaseScope phaseScope);

    ScoreDirector diversifyIndividual(ScoreDirector scoreDirector, double diversityRate);
}
