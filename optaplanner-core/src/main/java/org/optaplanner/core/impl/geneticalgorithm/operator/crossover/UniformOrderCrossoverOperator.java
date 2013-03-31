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

package org.optaplanner.core.impl.geneticalgorithm.operator.crossover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.optaplanner.core.impl.geneticalgorithm.Individual;
import org.optaplanner.core.impl.score.director.ScoreDirector;

public class UniformOrderCrossoverOperator extends AbstractChainingCrossoverOperator {

	@Override
	protected void performCrossover(ScoreDirector leftScoreDirector, ScoreDirector rightScoreDirector) {
		Individual leftParent = (Individual) leftScoreDirector.getWorkingSolution();
		Individual rightParent = (Individual) rightScoreDirector.getWorkingSolution();

		List<Object> leftEntityList = getOrderedList(leftScoreDirector);
		List<Object> rightEntityList = getOrderedList(rightScoreDirector);

		//TODO can it be possible that their not same size?
		int entitySize = leftEntityList.size();
		if (rightEntityList.size() != entitySize) {
			throw new IllegalStateException("Trying to perform crossover with individuals which have a different" +
					" amount of entities. This is not possible, report bug.");
		}

		List<Long> originalLeftIds = new ArrayList<Long>();
		List<Long> originalRightIds = new ArrayList<Long>();

		for (int j = 0; j < entitySize; j++) {
			originalLeftIds.add(leftParent.getEntityId(leftEntityList.get(j)));
			originalRightIds.add(rightParent.getEntityId(rightEntityList.get(j)));
		}

		Set<Long> usedLeftIds = new HashSet<Long>();
		Set<Long> usedRightIds = new HashSet<Long>();
		for (int i = 0; i < entitySize; i++) {
			if (workingRandom.nextDouble() < 0.5) {
				usedLeftIds.add(originalLeftIds.get(i));
				usedRightIds.add(originalRightIds.get(i));
			}
		}

		int currentLeftIndex = 0;
		int currentRightIndex = 0;

		List<Long> newLeftIds = new ArrayList<Long>(originalLeftIds);
		List<Long> newRightIds = new ArrayList<Long>(originalRightIds);

		for (int i = 0; i < entitySize; i++) {
			if (!usedLeftIds.contains(originalRightIds.get(i))) {
				long fromLeftId = newLeftIds.get(currentLeftIndex);
				Object fromLeftEntity = leftEntityList.get(currentLeftIndex);
				long toLeftId = originalRightIds.get(i);
				Object toLeftEntity = null;
				for (int j = 0; j < entitySize; j++) {
					if (newLeftIds.get(j) == toLeftId) {
						toLeftEntity = leftEntityList.get(j);
						leftEntityList.set(j, fromLeftEntity);
						newLeftIds.set(j, fromLeftId);
					}
				}
				leftEntityList.set(currentLeftIndex, toLeftEntity);
				newLeftIds.set(currentLeftIndex, toLeftId);
				swapChainedValues(fromLeftEntity, toLeftEntity, leftScoreDirector);
				currentLeftIndex++;
				currentLeftIndex %= entitySize;
			}
			if (!usedRightIds.contains(originalLeftIds.get(i))) {
				long fromLeftId = newRightIds.get(currentRightIndex);
				Object fromRightEntity = rightEntityList.get(currentRightIndex);
				long toRightId = originalLeftIds.get(i);
				Object toRightEntity = null;
				for (int j = 0; j < entitySize; j++) {
					if (newRightIds.get(j) == toRightId) {
						toRightEntity = rightEntityList.get(j);
						rightEntityList.set(j, fromRightEntity);
						newRightIds.set(j, fromLeftId);
					}
				}
				rightEntityList.set(currentRightIndex, toRightEntity);
				newRightIds.set(currentRightIndex, toRightId);
				swapChainedValues(fromRightEntity, toRightEntity, rightScoreDirector);
				currentRightIndex++;
				currentRightIndex %= entitySize;
			}
		}
	}

}
