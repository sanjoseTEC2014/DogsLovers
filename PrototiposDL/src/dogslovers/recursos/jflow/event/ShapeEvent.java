/*
 * JFlow
 * Created by Tim De Pauw <http://pwnt.be/>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dogslovers.recursos.jflow.event;

import java.awt.event.MouseEvent;

import dogslovers.recursos.jflow.Shape;

public class ShapeEvent {
	private Shape shape;

	private MouseEvent mouseEvent;

	public ShapeEvent(Shape shape, MouseEvent mouseEvent) {
		setShape(shape);
		setMouseEvent(mouseEvent);
	}

	public ShapeEvent(Shape shape) {
		this(shape, null);
	}

	public Shape getShape() {
		return shape;
	}

	protected void setShape(Shape shape) {
		this.shape = shape;
	}

	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}

	protected void setMouseEvent(MouseEvent mouseEvent) {
		this.mouseEvent = mouseEvent;
	}
}
