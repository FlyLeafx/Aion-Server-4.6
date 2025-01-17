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

package com.aionemu.gameserver.model.templates.itemgroups;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import com.aionemu.gameserver.model.templates.rewards.CraftRecipe;

/**
 * @author Rolandas
 *
 */
/**
 * <p>
 * Java class for CraftRecipeGroup complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CraftRecipeGroup">
 *   &lt;complexContent>
 *     &lt;extension base="{}BonusItemGroup">
 *       &lt;sequence>
 *         &lt;element name="item" type="{}CraftRecipe" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CraftRecipeGroup")
public class CraftRecipeGroup extends CraftGroup {

	@XmlElement(name = "item")
	protected List<CraftRecipe> items;

	/**
	 * Gets the value of the item property.
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the item property.
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getItems().add(newItem);
	 * </pre>
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CraftRecipe
     * }
	 */
	public List<CraftRecipe> getItems() {
		if (items == null) {
			items = new ArrayList<CraftRecipe>();
		}
		return this.items;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.aionemu.gameserver.model.templates.itemgroups.ItemGroup#getRewards()
	 */
	@Override
	public ItemRaceEntry[] getRewards() {
		List<CraftRecipe> var = getItems();
		return var.toArray(new ItemRaceEntry[var.size()]);
	}
}
