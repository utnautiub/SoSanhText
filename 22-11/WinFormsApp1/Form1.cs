namespace WinFormsApp1
{
    public partial class Frm_soSanhVB : Form
    {
        public Frm_soSanhVB()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void btnTinhTuongTu_Click(object sender, EventArgs e)
        {
            if (this.txtVbA.Text.Trim() == "" || this.txtVbB.Text.Trim() == "") {
                MessageBox.Show("Văn bản không thể để trống, hãy tiếp tục nhập !");
            }
            else
            {
                this.lblResult.Text = ((int) ControlCls.calSimilar(this.txtVbA.Text, this.txtVbB.Text)).ToString() + "%";
            }
        }

        private void btnDong_Click(object sender, EventArgs e)
        {
            var result = MessageBox.Show("Bạn có chắc chắn muốn đóng?", "Xác nhận", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (result == DialogResult.Yes)
            {
                this.Close();
            }
        }

    }
}
