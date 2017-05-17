using JOB.DATA.Enum;
using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class UsuarioViewModel_VW
    {
        [Key]
        public Guid ID_USUARIO { get; set; }

        [Required]
        [Display(Name = "Nome")]
        public string NOME { get; set; }

        [Required]
        [Display(Name = "CPF")]
        public string CPF { get; set; }

        [Required]
        [Display(Name = "RG (UF)")]
        public EnumUF RgUF { get; set; }

        [Required]
        [Display(Name = "RG (NR)")]
        public string RgNR { get; set; }

        [Required]
        [Display(Name = "Dt. Nascimento")]
        [DataType(DataType.Date)]
        [Range(typeof(DateTime), "01/01/1753", "31/12/9999")]
        public DateTime DT_NASCIMENTO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Aprovado")]
        public bool APROVADO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Ativo")]
        public bool ATIVO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Bloqueado")]
        public bool BLOQUEADO { get; set; }

        //[DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Fixo")]
        public string ContatoFIXO { get; set; }

        [Required]
        //[DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Celular")]
        public string ContatoCELULAR { get; set; }
    }

    public class UsuarioViewModel
    {
        [Key]
        public Guid ID_USUARIO { get; set; }

        [Required]
        [Display(Name = "Nome")]
        public string NOME { get; set; }

        [Required]
        [Display(Name = "CPF")]
        public string CPF { get; set; }

        [Required]
        [Display(Name = "RG (UF)")]
        public EnumUF RgUF { get; set; }

        [Required]
        [Display(Name = "RG (NR)")]
        public string RgNR { get; set; }

        [Required]
        [Display(Name = "Dt. Nascimento")]
        [DataType(DataType.Date)]
        [Range(typeof(DateTime), "01/01/1753", "31/12/9999")]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime DT_NASCIMENTO { get; set; }

        [ScaffoldColumn(false)] //ScaffoldColumn(false) = não é gerado automaticamente nas views
        public DateTime DT_INCLUSAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_ALTERACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_APROVACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime DT_ORDENACAO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Aprovado")]
        public bool APROVADO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Ativo")]
        public bool ATIVO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Bloqueado")]
        public bool BLOQUEADO { get; set; }

        //[DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Fixo")]
        public string ContatoFIXO { get; set; }

        [Required]
        //[DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Celular")]
        public string ContatoCELULAR { get; set; }

        ////[Required]
        //[DataType(DataType.EmailAddress)]
        //[Display(Name = "Email")]
        //public string ContatoEMAIL { get; set; }

        [Required]
        [Display(Name = "UF")]
        public EnumUF EnderecoUF { get; set; }

        [Required]
        [Display(Name = "CEP")]
        public string EnderecoCEP { get; set; }

        [Required]
        [Display(Name = "Logradouro")]
        public string EnderecoLOGRADOURO { get; set; }

        [Display(Name = "Complemento")]
        public string EnderecoCOMPLEMENTO { get; set; }

        [Required]
        [Display(Name = "Bairro")]
        public string EnderecoBAIRRO { get; set; }

        [Required]
        [Display(Name = "Cidade")]
        public string EnderecoCIDADE { get; set; }
    }
}