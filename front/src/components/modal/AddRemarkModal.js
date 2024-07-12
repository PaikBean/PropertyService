// React, Next
import { useState } from 'react'

// Materials

import { Modal, Stack } from '@mui/material'

// Custom Components
import SaveToolbar from '../toolbar/SaveToolbar'
import RemarkTextField from '../textfield/RemarkTextField'

// Utils
import { fetchRegistClientRemark } from '@/pages/clientsLedger/api/fetchRegistClientRemark'

const AddRemarkModal = ({ open, handleClose, data }) => {
  const initialData = {
    managerId: data.managerId,
    clientId: data.clientId,
    remark: '',
  }

  const [remarkData, setReamarkData] = useState(initialData)

  const handleInputChange = (field, value) => {
    setReamarkData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSave = async () => {
    console.log(remarkData)
    try {
      const result = await fetchRegistClientRemark(remarkData)
      setReamarkData(initialData)
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

AddRemarkModal.displayName = 'AddRemarkModal'

export default AddRemarkModal
