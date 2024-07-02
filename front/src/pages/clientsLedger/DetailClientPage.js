import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid from '@/components/datagrid/CustomDataGrid'
import InputName from '@/components/textfield/InputName'
import InputName2 from '@/components/textfield/InputName2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import SingleToolbar from '@/components/toolbar/SingleToolbar'
import { Widgets } from '@mui/icons-material'
import { Box, Grid, Stack } from '@mui/material'
import { useState } from 'react'
import clientColumns from './ClientColumns'

const DetailClientPage = () => {
  const initialSearchCondition = {
    clientName: '',
    clientPhoneNumber: '',
  }
  const initialSearchData = {
    clientId: null,
    clientName: '',
    inflowType: '',
    clientPhoneNumber: '',
    picManger: '',
    scheduleList: [],
    showingPropertyList: [],
    remarkList: [],
  }

  const [searchCondition, setSearchCondition] = useState(initialSearchCondition)
  const [searchData, setSearchData] = useState(initialSearchData)

  const [clientRows, setClientRows] = useState([])

  const handleSearchInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSearch = async () => {}

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={5}>
        <SingleToolbar text={'고객 상세'} />
        <Grid
          container
          gap={2}
          sx={{
            '& .revenu-header-css': {
              backgroundColor: 'lightgrey',
            },
          }}
        >
          <Grid item xs={3}>
            <Stack spacing={1}>
              <Grid container gap={1}>
                <Grid item xs={8.5}>
                  <Stack spacing={2}>
                    <InputName2
                      label="고객"
                      value={searchCondition.clientName}
                      onChange={(e) => {
                        handleSearchInputChange('clientName', e.target.value)
                      }}
                      sx={{
                        '.MuiInputBase-input': { height: '10px' },
                        '.MuiInputLabel-root': {
                          top: '-6px',
                        },
                      }}
                      name="clientName"
                    />
                    <InputPhoneNumber
                      value={searchCondition.clientPhoneNumber}
                      onChange={(formattedPhoneNumber) =>
                        handleSearchInputChange(
                          'clientPhoneNumber',
                          formattedPhoneNumber
                        )
                      }
                      sx={{
                        '.MuiInputBase-input': { height: '10px' },
                        '.MuiInputLabel-root': {
                          top: '-6px',
                        },
                      }}
                      name="clientPhoneNumber"
                    />
                  </Stack>
                </Grid>
                <Grid item xs={3} display="flex" alignItems="center">
                  <SearchBtn onClick={handleSearch} height="100px" />
                </Grid>
              </Grid>
              <Grid item>
                <CustomDataGrid
                  rows={clientRows}
                  columns={clientColumns}
                  height={'63vh'}
                  columnVisibilityModel={{
                    clientId: false,
                  }}
                  showAll={true}
                  pageSize={10}
                  rowHeight={48}
                />
              </Grid>
            </Stack>
          </Grid>
          <Grid
            item
            xs={8.5}
            sx={{
              border: '1px solid black',
            }}
          >
            <Stack spacing={1}>
              <div>기본 정보</div>
              <div>일정 그리드</div>
              <div>보여줄 목록 그리드</div>
              <div>특이사항 그리드</div>
            </Stack>
          </Grid>
        </Grid>
      </Stack>
    </Box>
  )
}

DetailClientPage.displayName = 'DetailClientPage'

export default DetailClientPage
