import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import ManagerAutocomplete from '@/components/autocomplete/ManagerAutocomplete'
import BasicDatePicker from '@/components/datepicker/BasicDatePicker'
import SingleToolbar from '@/components/toolbar/SingleToolbar'
import { useState } from 'react'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import dayjs from 'dayjs'
import TransactionType from '@/components/autocomplete/TransactionType'
import SearchBtn from '@/components/button/SearchBtn'

import CustomDataGrid from '@/components/datagrid/CustomDataGrid'
import { fetchRevenueList } from './api/fetchSearchRevenueList'
import columns from './columns/columns'

const { Box, Stack, Grid, Typography, Divider } = require('@mui/material')

const RevenueListPage = () => {
  const initialData = {
    managerId: null,
    addressL1Id: null,
    addressL2Id: null,
    contractStartDate: '',
    contractEndDate: '',
    transactionType: null,
  }

  const [searchCondition, setSearchCondition] = useState(initialData)
  const [totalAmount, setTotalAmount] = useState(0)
  const [rows, setRows] = useState([])

  const handleInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSearch = async () => {
    try {
      console.log(searchCondition)
      const response = await fetchRevenueList(searchCondition)
      if (response.responseCode === 'SUCCESS') {
        setRows(response.data.revenueDtoList)
        setTotalAmount(response.data.totalCommission)
      } else {
        console.error('Failed to fetch revenue list:', response.message)
      }
    } catch (error) {
      console.error('Error fetching revenue list:', error)
    }
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={2}>
        <SingleToolbar text="매출 장부 목록" />
        <Divider></Divider>
        <Stack spacing={3}>
          <Divider sx={{ width: '100%', borderColor: 'white' }}></Divider>
          <Grid container gap={3}>
            <Grid item xs={1}>
              <ManagerAutocomplete
                value={searchCondition.managerId} // Add this line
                onChange={(value) => {
                  handleInputChange('managerId', value)
                }}
                label="담당 매니저"
              />
            </Grid>
            <Grid item xs={1.5}>
              <AddressL1
                value={searchCondition.addressL1Id} // Add this line
                onChange={(value) => {
                  handleInputChange('addressL1Id', value)
                }}
                label="시/도"
              />
            </Grid>
            <Grid item xs={1.5}>
              <AddressL2
                value={searchCondition.addressL2Id} // Add this line
                addressLevel1={searchCondition.addressL1Id}
                onChange={(value) => {
                  handleInputChange('addressL2Id', value)
                }}
                label="시/구/동"
              />
            </Grid>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <Grid item xs={1.3}>
                <BasicDatePicker
                  label="계약 시작일"
                  value={dayjs(searchCondition.contractStartDate)}
                  onChange={(value) => {
                    handleInputChange('contractStartDate', value.format('YYYYMMDD'))
                  }}
                />
              </Grid>
              <Grid item xs={1.3}>
                <BasicDatePicker
                  label="계약 종료일"
                  value={dayjs(searchCondition.contractEndDate)}
                  onChange={(value) => {
                    handleInputChange('contractEndDate', value.format('YYYYMMDD'))
                  }}
                />
              </Grid>
            </LocalizationProvider>
            <Grid item xs={1}>
              <TransactionType
                value={searchCondition.transactionType} // Add this line
                onChange={(value) => {
                  handleInputChange('transactionType', value)
                }}
              />
            </Grid>
            <Grid
              item
              xs={0.5}
              style={{ display: 'flex', justifyContent: 'center' }}
            >
              <SearchBtn onClick={handleSearch} />
            </Grid>
            <Grid
              item
              flexGrow={1}
              display="flex"
              justifyContent="flex-end"
              alignItems="flex-end"
            >
              <Typography variant="h6">{totalAmount} 원</Typography>
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
              rows={rows}
              columns={columns}
              height={'65vh'}
              columnVisibilityModel={{
                revenueId: false,
              }}
              showAll={false}
              pageSize={10}
              rowHeight={48}
            />
          </Grid>
        </Stack>
      </Stack>
    </Box>
  )
}

RevenueListPage.displayName = 'RevenueListPage'

export default RevenueListPage
