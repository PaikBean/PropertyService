import { DataGrid } from '@mui/x-data-grid'

const CustomDataGrid2 = ({
  rows,
  columns,
  columnVisibilityModel,
  height,
  checkboxSelection,
  onRowSelectionModelChange,
  onRowDoubleClick,
  showAll = false,
  pageSize = 10, // 기본 페이지 사이즈를 props로부터 받아옴
  rowHeight = 50, // 기본 행 높이를 40으로 설정 (원하는 값으로 수정 가능)
  getRowId, // 부모 컴포넌트에서 전달받은 getRowId 함수
}) => {
  const gridStyle = {
    '& .MuiDataGrid-cell': {
      borderBottom: '1px solid #e0e0e0',
    },
    '& .MuiDataGrid-columnHeaders': {
      borderBottom: '1px solid #e0e0e0',
      fontWeight: 'bold',
    },
    '.MuiDataGrid-columnHeaderTitleContainer': {
      backgroundColor: 'lightGrey',
    },
    borderTop: 1,
    borderColor: '#e0e0e0',
    borderRadius: '8px',
    height: height,
  }

  return (
    <DataGrid
      rows={rows}
      columns={columns}
      getRowId={getRowId} // 부모 컴포넌트에서 전달받은 getRowId 함수 사용
      checkboxSelection={checkboxSelection}
      onRowSelectionModelChange={onRowSelectionModelChange}
      onRowDoubleClick={onRowDoubleClick}
      columnVisibilityModel={columnVisibilityModel}
      hideFooter={showAll}
      pagination={!showAll}
      rowHeight={rowHeight}
      sx={gridStyle}
      disableSelectionOnClick={true} // 행 클릭 시 선택을 비활성화
    />
  )
}

CustomDataGrid2.displayName = 'CustomDataGrid2'

export default CustomDataGrid2