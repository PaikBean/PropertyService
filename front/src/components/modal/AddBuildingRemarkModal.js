// React, Next
import { useState } from 'react'

// Materials

import { Modal, Stack } from '@mui/material'

// Custom Components
import SaveToolbar from '../toolbar/SaveToolbar'
import RemarkTextField from '../textfield/RemarkTextField'

// Utils
import { fetchRegistClientRemark } from '@/pages/clientsLedger/api/fetchRegistClientRemark'
import { fetchRegistBuildingRemark } from '@/pages/salesLedger/api/fetchRegistBuildingRemark'

const AddBuildingRemarkModal = ({ open, handleClose, data }) => {
  const initialData = {
    buildingId: data.managerId,
    remark: '',
  }

  const [remarkData, setRemarkData] = useState(initialData)

  const handleInputChange = (field, value) => {
    setRemarkData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSave = async () => {
    console.log(remarkData)
    try {
      const requestData = {
        buildingId: data.buildingId,
        remark: remarkData.remark
      }
      console.log(requestData)
      const result = await fetchRegistBuildingRemark(requestData)
      if(result.responseCode == "SUCCESS"){
        setRemarkData(initialData)
      } else{
        throw new Error(result.message)
      }
      handleClose()
    } catch (error) {
      alert(error.message)
    }
    return
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
        <SaveToolbar text={'특이사항 추가'} onClick={handleSave}></SaveToolbar>
        <RemarkTextField
          value={remarkData.remark}
          onChange={(e) => {
            handleInputChange('remark', e.target.value)
          }}
          width={725}
          rows={2}
        />
      </Stack>
    </Modal>
  )
}

AddBuildingRemarkModal.displayName = 'AddBuildingRemarkModal'

export default AddBuildingRemarkModal
