namespace WinFormsApp1
{
    partial class Frm_soSanhVB
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            btnTinhTuongTu = new Button();
            btnDong = new Button();
            txtVbA = new TextBox();
            txtVbB = new TextBox();
            label4 = new Label();
            lblResult = new Label();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Segoe UI", 20.25F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label1.Location = new Point(184, 36);
            label1.Name = "label1";
            label1.Size = new Size(463, 37);
            label1.TabIndex = 6;
            label1.Text = "So sánh độ tương tự giữa hai văn bản";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Segoe UI", 15.75F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label2.Location = new Point(160, 96);
            label2.Name = "label2";
            label2.Size = new Size(108, 30);
            label2.TabIndex = 4;
            label2.Text = "Văn bản A";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Font = new Font("Segoe UI", 15.75F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label3.Location = new Point(557, 96);
            label3.Name = "label3";
            label3.Size = new Size(106, 30);
            label3.TabIndex = 5;
            label3.Text = "Văn bản B";
            // 
            // btnTinhTuongTu
            // 
            btnTinhTuongTu.Font = new Font("Segoe UI", 14.25F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnTinhTuongTu.Location = new Point(40, 470);
            btnTinhTuongTu.Name = "btnTinhTuongTu";
            btnTinhTuongTu.Size = new Size(245, 52);
            btnTinhTuongTu.TabIndex = 2;
            btnTinhTuongTu.Text = "Tính độ tương tự";
            btnTinhTuongTu.UseVisualStyleBackColor = true;
            btnTinhTuongTu.Click += btnTinhTuongTu_Click;
            // 
            // btnDong
            // 
            btnDong.Font = new Font("Segoe UI", 14.25F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnDong.Location = new Point(656, 470);
            btnDong.Name = "btnDong";
            btnDong.Size = new Size(146, 52);
            btnDong.TabIndex = 3;
            btnDong.Text = "Đóng";
            btnDong.UseVisualStyleBackColor = true;
            btnDong.Click += btnDong_Click;
            // 
            // txtVbA
            // 
            txtVbA.Font = new Font("Segoe UI", 15.75F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtVbA.Location = new Point(40, 132);
            txtVbA.Multiline = true;
            txtVbA.Name = "txtVbA";
            txtVbA.Size = new Size(362, 306);
            txtVbA.TabIndex = 0;
            // 
            // txtVbB
            // 
            txtVbB.Font = new Font("Segoe UI", 15.75F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtVbB.Location = new Point(437, 132);
            txtVbB.Multiline = true;
            txtVbB.Name = "txtVbB";
            txtVbB.Size = new Size(362, 306);
            txtVbB.TabIndex = 1;
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(316, 492);
            label4.Name = "label4";
            label4.Size = new Size(0, 15);
            label4.TabIndex = 7;
            // 
            // lblResult
            // 
            lblResult.AutoSize = true;
            lblResult.Font = new Font("Segoe UI", 15.75F, FontStyle.Regular, GraphicsUnit.Point, 0);
            lblResult.Location = new Point(312, 480);
            lblResult.Name = "lblResult";
            lblResult.Size = new Size(30, 30);
            lblResult.TabIndex = 4;
            lblResult.Text = "%";
            // 
            // Frm_soSanhVB
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(840, 553);
            Controls.Add(label4);
            Controls.Add(txtVbB);
            Controls.Add(txtVbA);
            Controls.Add(btnDong);
            Controls.Add(btnTinhTuongTu);
            Controls.Add(label3);
            Controls.Add(lblResult);
            Controls.Add(label2);
            Controls.Add(label1);
            Name = "Frm_soSanhVB";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "SoSanhVanBan";
            Load += Form1_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Label label2;
        private Label label3;
        private ListView lstView_VbB;
        private Button btnTinhTuongTu;
        private Button btnDong;
        private TextBox txtVbA;
        private TextBox txtVbB;
        private Label label4;
        private Label lblResult;
    }
}
