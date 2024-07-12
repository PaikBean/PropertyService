// React, Next
import { useState } from 'react'

// Materials
const { Modal, Box, Stack, Grid, IconButton } = require('@mui/material')
import RemoveIcon from '@mui/icons-material/Remove'

// Custom Components
import SaveToolbar from '../toolbar/SaveToolbar'
import AddIcon from '@mui/icons-material/Add'
import CustomDataGrid2 from '../datagrid/CustomDataGrid2'
import ManagerColumns from './columns/ManagerColumns'
import DepartmentMemberColumns from './columns/DepartmentMemberColumns'

// Utils

const HandleDepartmentMember = ({ open, handleClose, data }) => {
  const [departmentId, setDepartmentId] = useState(data.departmentId)

  const [managerRows, setManagerRows] = useState([])
  const [departmentManagerRows, setDepartmentRows] = useState([])

  const handleSelectManagerRows = () => {}

  const handleSelectDepartmentRows = () => {}

  const handleInsertDepartmentRows = () => {}

  const handleDeleteDepartmentRows = () => {}

  const handleSave = () => {}
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
          width: 1200,
          bgcolor: 'background.paper',
          boxShadow: 24,
          p: 5,
        }}
      >
        <SaveToolbar text={'부서원 관리'} onClick={handleSave}></SaveToolbar>
        <Grid
          container
          sx={{
            '& .revenu-header-css': {
              backgroundColor: 'lightgrey',
            },
          }}
        >
          <Grid item xs={7}>
            <CustomDataGrid2
              rows={managerRows}
              columns={ManagerColumns}
              height={'45vh'}
              columnVisibilityModel={{
                managerId: false,
              }}
              checkboxSelection={true}
              onRowSelectionModelChange={handleSelectManagerRows}
              showAll={true}
              pageSize={10}
              rowHeight={48}
              getRowId={(row) => row.managerId}
            />
          </Grid>
          <Grid
            item
            xs={1}
            display="flex"
            alignItems="center"
            justifyContent="center"
          >
            <Stack direction="column" spacing={2}>
              <IconButton onClick={handleInsertDepartmentRows} size="large">
                <AddIcon fontSize="large" />
              </IconButton>
              <IconButton onClick={handleDeleteDepartmentRows} size="large">
                <RemoveIcon fontSize="large" />
              </IconButton>
            </Stack>
          </Grid>
          <Grid item xs={4}>
            <CustomDataGrid2
              rows={departmentManagerRows}
              columns={DepartmentMemberColumns}
              height={'45vh'}
              columnVisibilityModel={{
                managerId: false,
              }}
              checkboxSelection={true}
              onRowSelectionModelChange={handleSelectDepartmentRows}
              showAll={true}
              pageSize={10}
              rowHeight={48}
              getRowId={(row) => row.managerId}
            />
          </Grid>
        </Grid>
      </Stack>
    </Modal>
  )
}

HandleDepartmentMember.displayName = 'HandleDepartmentMember'

export default HandleDepartmentMember
