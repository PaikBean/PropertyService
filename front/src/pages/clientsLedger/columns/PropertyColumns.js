const propertyColumns = [
  {
    field: 'propertyId',
    headerName: '매물Id',
    flex: 1,
    headerAlign: 'center',
    align: 'center',
    headerClassName: 'revenu-header-css',
  },
  {
    field: 'transactionType',
    headerName: '거래유형',
    flex: 1,
    headerAlign: 'center',
    align: 'center',
    headerClassName: 'revenu-header-css',
  },
  {
    field: 'address',
    headerName: '주소',
    headerAlign: 'center',
    flex: 4,
    headerClassName: 'revenu-header-css',
  },
  {
    field: 'price',
    headerName: '가격',
    flex: 1,
    headerAlign: 'cashValue',
    align: 'center',
    headerClassName: 'revenu-header-css',
  },
]

export default propertyColumns
