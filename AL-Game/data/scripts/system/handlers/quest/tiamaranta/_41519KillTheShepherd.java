/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */

package quest.tiamaranta;

import java.util.ArrayList;
import java.util.List;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;

/**
 * @author Cheatkiller
 *
 */
public class _41519KillTheShepherd extends QuestHandler {

	private final static int questId = 41519;
	private final List<Npc> junes = new ArrayList<Npc>();

	public _41519KillTheShepherd() {
		super(questId);
	}

	@Override
	public void register() {
		qe.registerQuestNpc(205938).addOnQuestStart(questId);
		qe.registerQuestNpc(701316).addOnTalkEvent(questId);
		qe.registerQuestNpc(218333).addOnKillEvent(questId);
		qe.registerQuestNpc(205914).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		DialogAction dialog = env.getDialog();
		int targetId = env.getTargetId();

		if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			if (targetId == 205938) {
				if (dialog == DialogAction.QUEST_SELECT) {
					return sendQuestDialog(env, 4762);
				} else {
					return sendQuestStartDialog(env);
				}
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 701316) {
				Npc npc = (Npc) env.getVisibleObject();
				junes.add((Npc) QuestService.spawnQuestNpc(npc.getWorldId(), npc.getInstanceId(), 701261, npc.getX() + 2, npc.getY() + 2, npc.getZ(),
						npc.getHeading()));
				junes.add((Npc) QuestService.spawnQuestNpc(npc.getWorldId(), npc.getInstanceId(), 701261, npc.getX() - 2, npc.getY() + 2, npc.getZ(),
						npc.getHeading()));
				junes.add((Npc) QuestService.spawnQuestNpc(npc.getWorldId(), npc.getInstanceId(), 701261, npc.getX() - 2, npc.getY() - 2, npc.getZ(),
						npc.getHeading()));
				return useQuestObject(env, 0, 1, false, false);
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 205914) {
				switch (dialog) {
					case USE_OBJECT: {
						return sendQuestDialog(env, 10002);
					}
					default: {
						return sendQuestEndDialog(env);
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean onKillEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var == 1) {
				for (Npc june : junes) {
					june.getController().onDelete();
				}
				return defaultOnKillEvent(env, 218333, 1, true);
			}
		}
		return false;
	}
}
