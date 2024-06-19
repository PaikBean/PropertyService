import { DataGrid } from '@mui/x-data-grid'

const CustomDataGrid = ({ rows, columns }) => {
  return <DataGrid rows={rows} columns={columns} />
}

CustomDataGrid.displayName = 'SaveBtn'

export default CustomDataGrid
