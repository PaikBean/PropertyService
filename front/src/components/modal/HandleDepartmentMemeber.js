import { useEffect, useState } from 'react'
import { Modal, Stack, Grid, IconButton } from '@mui/material'
import RemoveIcon from '@mui/icons-material/Remove'
import AddIcon from '@mui/icons-material/Add'
import CustomDataGrid2 from '../datagrid/CustomDataGrid2'
import ManagerColumns from './columns/ManagerColumns'
import DepartmentMemberColumns from './columns/DepartmentMemberColumns'
import { fetchSearchCompanytMemeberListInModal } from '@/pages/manageCompany/api/fetchSearchCompanytMemeberListInModal'
import { fetchSearchDepartmentMemeberList } from '@/pages/manageCompany/api/fetchSearchDepartmentMemberList'
import SaveToolbar from '../toolbar/SaveToolbar'
import { fetchUpdateDepartment } from '@/pages/manageCompany/api/fetchUpdateDepartment'
import { fetchUpdateDepartmentMemberList } from '@/pages/manageCompany/api/fetchUpdateDepartmentMemberList'

const HandleDepartmentMember = ({ open, handleClose, data }) => {
  const [departmentId, setDepartmentId] = useState(data.departmentId)
  const [managerRows, setManagerRows] = useState([])
  const [departmentManagerRows, setDepartmentRows] = useState([])

  // 추가된 상태 변수들
  const [selectedManagerRows, setSelectedManagerRows] = useState([])
  const [selectedDepartmentRows, setSelectedDepartmentRows] = useState([])

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result1 = await fetchSearchCompanytMemeberListInModal(data.companyId)
        const result2 = await fetchSearchDepartmentMemeberList(data.departmentId)
        const companyMemberList = result1.data.managerInfoDtoList
        const departmentMemberList = result2.data
        setManagerRows(companyMemberList)
        setDepartmentRows(departmentMemberList)
      } catch (error) {
        alert(error)
      }
    }

    if (open) {
      fetchData()
    }
  }, [data, open])

  // 왼쪽 그리드에서 선택된 행을 관리하는 핸들러
  const handleSelectManagerRows = (ids) => {
    setSelectedManagerRows(ids)  // 선택된 행의 ID를 배열로 저장
  }

  // 오른쪽 그리드에서 선택된 행을 관리하는 핸들러
  const handleSelectDepartmentRows = (ids) => {
    setSelectedDepartmentRows(ids)  // 선택된 행의 ID를 배열로 저장
  }

  // 선택된 부서원을 오른쪽 그리드로 이동하는 함수
  const handleInsertDepartmentRows = () => {
    // 이미 부서원에 존재하는 멤버는 추가하지 않도록 필터링
    const newMembers = managerRows.filter(
      (row) => selectedManagerRows.includes(row.managerId) && !departmentManagerRows.some(departmentRow => departmentRow.managerId === row.managerId)
    )

    // 새로운 멤버만 우측 그리드에 추가
    setDepartmentRows([...departmentManagerRows, ...newMembers])
    // 선택된 행 초기화
    setSelectedManagerRows([])
  }

  // 오른쪽 그리드에서 선택된 부서원을 제거하여 왼쪽으로 이동하는 함수
  const handleDeleteDepartmentRows = () => {
    const remainingDepartments = departmentManagerRows.filter((row) => !selectedDepartmentRows.includes(row.managerId))
    setDepartmentRows(remainingDepartments)
    // 선택된 행 초기화
    setSelectedDepartmentRows([])
  }

  const handleSave = async () => {
    const managerIdList = departmentManagerRows.map(row => row.managerId);
    try {
      const result = await fetchUpdateDepartmentMemberList({departmentId: data.departmentId, managerIdList: managerIdList})
      if(result.responseCode === "SUCCESS"){
        setSelectedManagerRows([])
        setSelectedManagerRows([])
      } else{
        throw new Error(result.message || 'Error!')
      }
    } catch (error) {
      alert(error)
    }
    handleClose(false)
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
              onRowSelectionModelChange={handleSelectManagerRows}  // 여기서 선택된 행을 업데이트
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
              onRowSelectionModelChange={handleSelectDepartmentRows}  // 여기서 선택된 행을 업데이트
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

export default HandleDepartmentMember