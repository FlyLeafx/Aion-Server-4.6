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

package quest.sarpan;

import com.aionemu.gameserver.model.DialogAction;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;

/**
 * @author zhkchi
 */
public class _11524FieldRepairs extends QuestHandler {

	private final static int questId = 11524;

	public _11524FieldRepairs() {
		super(questId);
	}

	@Override
	public void register() {
		qe.registerQuestNpc(205533).addOnQuestStart(questId);
		qe.registerQuestNpc(205533).addOnTalkEvent(questId);
		qe.registerQuestNpc(205562).addOnTalkEvent(questId);
		qe.registerQuestNpc(205536).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		DialogAction dialog = env.getDialog();

		int targetId = env.getTargetId();

		if (qs == null || qs.getStatus() == QuestStatus.NONE || qs.canRepeat()) {
			if (targetId == 205533) {
				switch (dialog) {
					case QUEST_SELECT:
						return sendQuestDialog(env, 1011);
					case QUEST_ACCEPT_SIMPLE:
						return sendQuestStartDialog(env);
					default:
						break;
				}
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 205536) {
				switch (dialog) {
					case QUEST_SELECT:
						return sendQuestDialog(env, 1352);
					case SETPRO1:
						return defaultCloseDialog(env, 0, 1);
					default:
						break;
				}
			} else if (targetId == 205562) {
				switch (dialog) {
					case QUEST_SELECT:
						return sendQuestDialog(env, 1693);
					case SETPRO2:
						return defaultCloseDialog(env, 1, 2);
					default:
						break;
				}
			} else if (targetId == 205533) {
				switch (dialog) {
					case QUEST_SELECT:
						return sendQuestDialog(env, 2375);
					case SELECT_QUEST_REWARD:
						changeQuestStep(env, 2, 2, true);
						return sendQuestDialog(env, 5);
					default:
						break;
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 205533) {
				return sendQuestEndDialog(env);
			}
		}
		return false;
	}
}
