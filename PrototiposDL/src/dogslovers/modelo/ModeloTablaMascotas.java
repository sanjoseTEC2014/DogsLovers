package dogslovers.modelo;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import dogslovers.control.MaquinaEstadosMascotas;

public class ModeloTablaMascotas extends AbstractTableModel {
	private ArrayList<Mascota> listaMascotas;
	private String[] titulos = {"ID", "Nombre", "Especie", "Raza", "Estado Actual", "Qui�n registr� el �ltimo Suceso"};

	public ModeloTablaMascotas(ArrayList<Mascota> pListaMascotas){
		listaMascotas = pListaMascotas;
	}
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listaMascotas.get(rowIndex).getID();
		case 1:
			return listaMascotas.get(rowIndex).getNombre();
		case 3:
			return listaMascotas.get(rowIndex).getEspecie();
		case 4:
			return listaMascotas.get(rowIndex).getRaza();
		case 5:
			return listaMascotas.get(rowIndex).getEstadoActual();
		case 6:
			return listaMascotas.get(rowIndex).getUltimoSuceso().getNick();
		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public int getRowCount() {
		return listaMascotas.size();
	}
	
	public int getCantidadDeResultados(){
		return listaMascotas.size();
	}

}
