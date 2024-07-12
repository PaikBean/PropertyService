// React, Next
import { useState } from 'react'

// Materials
import { Grid, Modal, Stack } from '@mui/material'

// Custom Components
import AddressL1 from '../autocomplete/AddressL1'
import AddressL2 from '../autocomplete/AddressL2'
import TransactionType from '../autocomplete/TransactionType'
import SearchBtn from '../button/SearchBtn'
import CustomDataGrid from '../datagrid/CustomDataGrid'
import SaveToolbar from '../toolbar/SaveToolbar'

// Utils
import propertyColumns from '@/pages/clientsLedger/columns/PropertyColumns'
import { fetchSearchProperties } from '@/pages/clientsLedger/api/fetchSearchProperties'

const AddPropertyModal = ({ open, handleClose, data, onClick }) => {
  const initialSearchData = {
    addressL1: null,
    addressL2: null,
    transactionTypeId: null,
    propertyTypeId: null,
  }

  const [searchCondition, setSearchCondition] = useState(initialSearchData)

  const [searchRows, setSearchRows] = useState([])
  const [selectedRowIds, setSelectedRowIds] = useState([])

  const handleSearchConditionInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSearch = async () => {
    try {
      console.log(searchCondition)
      const response = await fetchSearchProperties(searchCondition)
      if (response.responseCode === 'SUCCESS') {
        setSearchRows(response.data)
      } else {
        console.error('Failed to fetch revenue list:', response.message)
      }
    } catch (error) {
      console.error('Error fetching revenue list:', error)
    }
  }

  const handleSelectSearchRows = (selectionModel) => {
    setSelectedRowIds(selectionModel)
  }

  const handleSave = () => {
    console.log(searchCondition)
    // 보여줄 목록 저장해야댐. api 수정 해야됨
    handleClose()
  }

  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="data-modal-title"
      aria-describedby="data-modal-content"
    >
      <Stack
        spacing={3}
        sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          width: 800,
          bgcolor: 'background.paper',
          boxShadow: 24,
          p: 5,
        }}
      >
        <SaveToolbar
          text={'보여줄 매물 목록 추가'}
          onClick={handleSave}
        ></SaveToolbar>
        <Stack spacing={2}>
          <Grid container gap={1}>
            <Grid item xs={3}>
              <AddressL1
                value={searchCondition.addressL1} // Add this line
                onChange={(value) => {
                  handleSearchConditionInputChange('addressL1', value)
                }}
              />
            </Grid>
            <Grid item xs={4}>
              <AddressL2
                value={searchCondition.addressL2} // Add this line
                addressLevel1={searchCondition.addressL1}
                onChange={(value) => {
                  handleSearchConditionInputChange('addressL2', value)
                }}
              />
            </Grid>
            <Grid item xs={2}>
              <TransactionType
                value={searchCondition.transactionTypeId} // Add this line
                onChange={(value) => {
                  handleSearchConditionInputChange('transactionTypeId', value)
                }}
              />
            </Grid>
            <Grid
              item
              xs={1}
              style={{ display: 'flex', justifyContent: 'center' }}
            >
              <SearchBtn onClick={handleSearch} />
            </Grid>
          </Grid>
          <Grid
            item
            sx={{
              '& .revenu-header-css': {
                backgroundColor: 'lightgrey',
              },
            }}
          >
            <CustomDataGrid
              rows={searchRows}
              columns={propertyColumns}
              height={'45vh'}
              columnVisibilityModel={{
                propertyId: false,
              }}
              checkboxSelection={true}
              onRowSelectionModelChange={handleSelectSearchRows}
              showAll={true}
              pageSize={10}
              rowHeight={48}
            />
          </Grid>
        </Stack>
      </Stack>
    </Modal>
  )
}

AddPropertyModal.displayName = 'AddPropertyModal'

export default AddPropertyModal
