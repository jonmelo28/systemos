-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 09-Jul-2022 às 00:32
-- Versão do servidor: 5.7.36
-- versão do PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `os`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `filial`
--

DROP TABLE IF EXISTS `filial`;
CREATE TABLE IF NOT EXISTS `filial` (
  `idFilial` int(11) NOT NULL AUTO_INCREMENT,
  `empresa` varchar(50) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `fone` varchar(15) NOT NULL,
  `site` varchar(100) NOT NULL,
  `imagem` blob NOT NULL,
  PRIMARY KEY (`idFilial`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `filial`
--

INSERT INTO `filial` (`idFilial`, `empresa`, `endereco`, `bairro`, `cnpj`, `cep`, `cidade`, `fone`, `site`, `imagem`) VALUES
(1, 'DR. MUAMBA', 'RUA OBDC,12345', 'OBDC', '12.345.678/0001-01', '49500-000', 'ITABAIANA-SE', '7999935-6828', 'www.drmuamba.com.br', 0x89504e470d0a1a0a0000000d49484452000000a0000000a008060000008bcf672d000000017352474200aece1ce90000000467414d410000b18f0bfc6105000000097048597300000ec400000ec401952b0e1b00001c7749444154785eed9d095c5455fbc77f2cc30ec30e8222eebbb8ef4be69269b9e5526699bea5e5eb9699596af5badb666a9aff6cd52ccbccdc32432477dc371001055140100461d80618e67fce9933328c03026283f79e6f9f71b8cfb97786667e3ccf79ce39f739165a0204023361c99f0502b3200428302b428002b3220428302b428002b3220428302b428002b3220428302b428002b32266422a41c8f9681c8988c5ae1357107933195696babfdf224d317ab4a887714fb445fbc6016851d797d9050f4608b002acda71181fff168ac4d824c09a884e614d620779b6208dfa4f4fa301d48580bd2d3ab5a88fcf270f45d76681bc5150164280e5b0fdd8254cfcec17dc4d4a051ceda8ab038a8b4b4477ef07aa44fa449ead88302dc9736636da776d855d1ffe07b5dc5d74ed82fb10022c8327e6acc5c1d0b3808ba3ceb3595be1b5e1bd30b46b4b0ceed49c9f554246762ed6ee3a8a43e1b108de1b0628c975d44ba6dec58cff8e201e71183f53608810a011896999a83f71090a3273585875f7f3c2fb2ff6c78ca1bdf8190fa69078caa9eb7ec757bf860076b644c00508a8ef87c8afe6c2c156c1cf125084000d38161987ee535792c84a3e92420dde7ee569acf8cfb3fa005b696212d33060fe7a5cbf9a4842b315949e4a24fcf8019cec6cf819022140ceb96b09683761290bb52059edb6a59331a27b6bdefa704c23def08bcd7f03c4fbd939db23e78f15a49b5855594b0b214042862a17ee03df64d9ada5833d6efdf421bc5d9d786bf5b0e764249e99f20988fb43fd7a7eb8f6dd3cde226f74035932c7ffa5852c443a7b28a1f9f3936a171f6570a76638ba7101505084d8e89b18b9f407de226f642fc051440879b73360a37442ea96ff71eba3a15bf3407cbff035d2c724219e64ccbf1fbdc45be48bac05b8332c02bfed3cc2c6ee4eae9a41ba688f3e431ddfaf03c60eefcddef3b985dfa2988851cec85a8043dfdfc0069657cf1e8ba0fafedcfae8d9fcf68bf0f0f702b2733168c1d7dc2a4f642bc0918b491f2c2b076d3bb7c0b4a13db9f5df237ac35c227e2df6ed3d8e1357e2b9557ec85280314969a40f769864bd5638f4d17fb9f5dfc5ddc5119f124f086b6b0ca29e58a6c852802faef8914de32e983c0c4ef6b6dcfaef336b446f38f97b223d3e19dfec0be35679213b019eb99a8053c7c30137672c1c37905bcdc7d6f7c6b350fcde777bb9455ec84e80d3d76d6773bcdfcf19cb2de6656087a6681cd410b7a3e3117c2e9a5be583ac04782b3d0bc78e9c87635d5f8cefdb915bcdcffae9a3000b4bb680416ec84a800be97c6c5131de7f6100b7d40cfab46e88dacd03114d3ce0f594746e9507b212e0fadd47015747cc19d5875b6a0eabdf18ce16bc7eb18bfc8e324236023c703e0620de6574ff4edc52b318dead15498c5cb08efe91c808d90870f9d6036ce8e583b1352bfc1af25cbf0ec88b4d624bc3e4826c04181c160ea7867e685e83ef587b87770d7ed87f863dcb0159ac070cbb128fae6316603ae967ad7a9df4b52a4152663ebe3e7283dd7e645981b5d1f43c4d7131826a2b31a26de5c56ef1f42c78bb3a23e5e747bb32a7a6200b01ce58bf1dabbfdc8eb05f17a37393006e7d30c5e4a3b17a7db74e55f44eb7ca905784e77b06e0e757db7143c5b01bb31cea9bd790b97f0d5c1cecb855bac822046fa677b7b9bb544a7c94a57baf32f1291d6da0b45754eee16e8f2da1d791995fc85fedc174fdf838bab76a021414b2a5627240f202cc275fe69d8454b4695e8f5b2acedef0dbb0b5b12221558bfc424da51e2cb0382ab0fd6c327fb5f2f9eee80d282c2c10327f2889e1246bbf40b276192079014625a401aa1cb624be2a688a89f32442ead6c01d9d025d2bf4e841ce5517910b89a0eee414f0572a9b4c12aedfdb118543b3bb90234bd8d4f1c6f663e1ba46a943fb805266d996fd5ab47e491b7c368a5b2a4eb78f8e68f1ea4eedd49f2f714bc5097c2f845dfbd1beabdc52368d16846a4fc565f023adb6cf9c2fb4e8f98656a3d1708b7491bc073c4febb9104f54d9fe9f216a12522b8bb3ad35fdebd685e27298b32d122f75f64307e239f5c4a86c81ec1c442711ef2d71242fc03da722e1dcc00fce0f9151566598c0ca8afc432e7423094c59fc199e82b33732b16070636e016acd0d8195a33740fa9e6cf646e2485e80d979f9707574e0475543410b0e55928b092af2e95a10219a1ebe51e51761d6d648ec7f93f6fb743cb9320cc9996a646b88f75458e14ac26dde225d242d40559e1ab89b8deecd1fa24c1ac9801b7a3bf2838a534cae6394e13efb11b11d7cab447caf6eba80d0cba970b2b144b1850decdd5c713afa266f952e9216205dff07552e9a06f8704bd5b0aaec2034a59c6b46ac3f8dd5cfb7848f8bae5b30777b24be098d27dd0405b289075cfb62107cdc9c714a08f0f126333b8f2520deca87ab7450953e6059bcf1d3258cef52079debe9928ee9bf8463c5ce68383829a0caccc78e595df0423b2fd8dad8a0a8b8f2c9cfe386a405189b9cce32d1a07ab5b8e5e199f94b042c26ee80f38cbf10f0ee7ef8bdb31f21574c64ab7ad51aa8f7d3e06b6c9c70681b9d479eba25026b8263a1209e8f26cb9796f4c590d6bab64e346b57e53d308b7edc91b4002fc4e98660fc3d4b86382a83a920faf99816e8d4d803937bd5c58d65fd104a3c56bfe547b137c2286130d2cd8f2712996942b73aec78dcb7e7b036249655480870b747fcb227d1d2df99b55102bcc8ef9c95839ba977b9459a485a8079b4b2294159c52198428d69ef1354db05363cbb6de2e384df6774c6a8af4c2fa172b6b3c6d1abe924bbcdc7ecfe0d988d66bb9b8febd6fc0d6ee58dab8bfac0cba9f4eda10eb63634934111ad3d2d61242dc042de87b2ac4a1241c85193eb4d5c4a476568a9683dc3dbfa2227f62eaea7e5728b0e4b6b4becb99482c8e41ccc1e40c5a745fdf907104abda5ba08cb4735c7eea9a657685b10cf4ddfbca88c3f02a9206d01d2a2e2442d552d0659d665164c95a585d1ae6b6d36b06c48b1a6186d029478b5471d84c566c0e2951d884b5421c0db11496b06e11d264ad3d8d14afc84024d117b962a9216607e01f9f26c15b0e15f66b5614298de248446df2eed0129fe4a5b7c79f03aba2e3a4454658d39cf3646fcd2bea845ece5e1418ba3930424bf4084e0c716752111a0b535acab309351596cad2d9092a5e6473ae852aecf0f5cc7940d67d1b6a13ba2fef704568ca8d8aa1cf6bb13a157b1f7f0d8206901dad910cfa72ed07d9955e05e9035ea86dd1f8069b8b660d36bc664e61662cbcc2e383baf271a9384a5a2a4aba837b520de9b4e2a4b17490bd09166924480855558cd528a0ae40174d16a63dfd20253932466c1e04618d3c18f5b2a0ebd1d80a260ab1aa48ba405a8f31e16d056318c955c76bf028d5f3235bb0081eef6fca80496495701bd00ff8dee833991f4ff9d154f631f36049b7280c61972d8a51474a9e7c68f1e9eccdc7cf2ed584241fab05246d202acebe3c19e93d355ecf95111959ccd9e3bf1f9dd5254d1fb26a565024ef6a8e556323b2245242dc0d66c0e588b88f85b3a433511733b17ce762505cd9b7ef80f16bdd0921f550f113792d99e220aba718e8491b4007d5da9f7b0401c5d94500574b311a5f968df55041fbb81a57fc5a0d5c283b0786d17de1dd810f30d563557074977b26029f1f04b91b4009de916ab0a6b24d0705649765f4c41761e1d8bd3cd7be819dfb50ed2be198ab8c57db16f7a67e4ad1b84a5c39af2d6eae34e560e02bcaaaf4f595391b400fde83ebd9e4afc7d368a5b1eccafa713d912ab257f5d85ab83de03e924489746f9b8d8c2c3c9065ece36f073b583dd2318a7cb220988262915bd5bd7e716e9226901521c1ded713b53972494c789b80c34f9201463561cc3e7a35ae0f89ceec8a643287438847f4affd6dabc74e2fd68214d5d1742da485e80833a36852a3e8555483045567e1106ac0e43977743e0646b0dedd691e818a8c4a9eb77490e50ba0f664937a0fe17a077f251a7dbbf5d136e912e921760ab7afeacd6ca89a81bdc52c227c1d7a09cf11782cfa560f1cb4138f35e4f9cbfa942dc9d5c22425714d2b20866e048c47536875d934bc955179217e0b36c7b7d6da90af44977f3d171d911bcbd2502f63656d8fb5657cc1bd408c191a948b89b87279b78ea4ee411b72265d9aa93105a17c6db4df2638014c90bb045a00fa074c6ae13ba6a535b4892519b84dbd3d7d2e1a6b445e4874f60604b6f6c0a4b602ba09f696574071dc982abe2094d8ce054083aa79c7a33052daaf13e969a8c2cea03ba8d9a87dcbc7c8c1f3b011bf6d3fb302cd0a28e0bc2dfefcddabf3b7a93dd40fe7297daec584ff30f0f22362d070ec44bd6f374a0b708335fa85f229549fa8f36569668e9e74cb2614be630a9b7541769b0fd7c0ad40545f8e6953698c8ef03a90821e7a3d16ffc12ccfcef08ac9c34945ba58be43d20e5e5273ba2202313db8e4591ff634b0c245e4e2fbe2f0fc5a3b0b8f83ef1519c6c751f0f5d167ff95636ae246723923c22c8cf67ae6560c5f0667024e2fcf574127e3a99889fc963f3c9046c3b97ccd60752c5fa38975d9ac3149b0e9c6599f72bfd6ace3e268f125908705cdf76746d3b8ab39331a14f7dec9daebb0f63dbd95bb89da5c6a49e75d9b13143dbf8424dbc1c0da7b6d696f71ed4eb5191d012bc23dbd7820571898e2483d63fa8c7648511c83f838d43fa03d876e422e0e386a0fa955fc2f538220b01766c1c40fa812e506726e2db1769524232cdabe9c453ddc207cf943d8546a7d87cdded91955d80cc9c9247566e012b1cb8ecafab58137a1d5a22ee7bedb985ec395ba5c6ec4a4ecfc524a6223b3e19dd821a728bf491451f90d26bce17381c7c1a097f7f0a6717370c58790c61737bf0d6f2f9e6e80dd2b323ff192516597945248bb6bc57bc8836e71616a3b0a8187e6eb618d5ae725e6ccaba6df872c32e6cfc641a5eeadb9e5ba58d6c04b82b2c0243a67c8c51a307e0a675431c9f5df336acb118f8265dc10aede175dc227d64118229cf7669c1b668ddbaef240ebc59f3c4c7e6ab5333f144b7ea5dd655d3918d0029837b0401aa2cfc15463afa358cd95fef62317cdef3fdb8451ec84a807346f661b31b33bfdac12d35838cec3c5c3a170d2b5f77f46b5bbdeb0a6b3ab21260af560de0dfbc2e6e84c722f4c2556e353f23977ccf76cafc68c233dc221f642540ca9a37469050678131cb37728b79b9109b880307ce00f6b698f5dc13dc2a1f642740ba2daa7b7d7fa4c6dec2c6fda7b9d57c8c59be89750b1656720f3ba9203b0152be9d399afcabc5a4355b750633f1c3fe5388ba780d169eae5830b63fb7ca0b590a7028f1828d5b37843af90edef8621bb7fefbbcb28c740334c5583ff5396e911fb214202564e9eba42f6889f51bf7e2a489c5aa8f9a5e6faf05f20ad0aa63334c1ad4955be5876c0558dbcb15efd0e54e9616e8397b0db7fe3b7cfbf7091c3e7c9ebdf7c11553b8559ec8568094e51307c32bb0160a3254089af231b73e5a6ea666e03fcb7f6499f8a2a923e1e6fc709be83ceec85a8094a39f4d63fdb08b17ae62f8a2efb8f5d1909da746bd094b68e95604b56d8cf9329bf530856c162394c7d6c317307ae62a564d75c093edb16ff124de527ddcc9ca85e7c8f7d80d52d4fb6943bfe02df246f61e9032aa671066d2fea04683bf0f9d47fb699ff196eae168441c3c9f798b793e585b2169db52de221002e4ac9c3c14afbe308089f0eca56b701cf60e2ed27d461e92f93fec458f299f806d9f696589a81f16a016add8206088106cc4cb1f6fc6a61d87697d5f122e8bf06cdff6f869ce3838d9975f54dc98df8f5ec48cf57f20818ad8d2124a6f57446d980b1f37213e4384004db0332c1c43df5c43f74a60fd3564e660c0c0ce78a95f078ceb53fe4ae50d7bc3b062eb015c236197d6f7a3bb1df526fdca7f643edc52164280e5d077ee3a1c083903b83a81ed4c43b77d5017c0c1cf1bbd5ad7476d0f25cb6cc3e393117efa0aebdfd16a5c74af5f7aaec2d5199b66bf8031bddbf25714182304f800769db88c77bfdb8d88cbd7c911f9a8ec4828a6b7bcd12db4d847473c24bd51988a8fda73f2e0e8eb8e69437a62d984c1ec350465230458416842127c2e069f6f3fc8caa715146958ed692bd2bfb3b755b06af67e1e2e58fcf2d36cdda11b0dbf820722045845d2b37291aaca86bd42c1b25aa997d27d5408010acc8a1807149815214081591102149815214081591102ac04345f2b2f672b36dc465d5021840005664508b012d09d934ced9ea4a7bc3681698400ab1121c0ca230428302b622684137f3b0327aec4c3c1ce8624135a146934ac627dfd5a1e68dff0fefad185451afc7af83c940eba39df82a2221491f37b340f849f8792d9040f46089043c53466d1f7c0dd6c1015c2dddb0db90585c8cfcc46401d5f9c5933139eca922df9d93d1ea3e70174234485353c027c9193af467e9e1a033a35c3bec593f9998272a102149480bed3b54fcd5bcf8fb4daf3d712b51ea3e66bd17b0ab794a6de84c55acb41b3f891567b2422568b7613b4433efc865b04e521fa8046d4f5f72cb5310dad569ff6eb2220bf10c3167ecbad250ce9d41cc5851a7e04746f5e0f2be6be849d5b43a0a5eb0705e522046804958c29e17c30f539ecf82504b9f905dca28365be46a73fddb129b3ed3e71995b046521046882d25b54eb9836a4075bf1bcf140e9926ea67c5c4392b840a140dcadaaedd42e2784004d604a801e2e8e808f3bf69c8ce4161dba334bcbd0d2827cac165a149b94a7c01021402348bf98ff743f8d1af9e34ccc4d7e5436b905244c936ea49264d382f2110234828e0196451d77256ec5dee2473a0ccfd68bf7727c0aa0ca65d55805e52304684479b3691e741c302f9f1f19c02fd24fc5addf7b1c0dfbb485abb831e98108011a51deb6fca64aa9e9f54aef94bb959e85cda167f1e31f8770e89369bc45501e428095804ecf19e72756749f38223e65af29f0eb3f03e3667c8e943d9fc862b7f3ea4008b012dca6d374465d440d1db4b651407bfa5b6c5f338b762271e1dac31735920b428095808658ba97af214c8fb4f21561184d3a88e79b55c37662aac908015682eb29e96851cf9f1f9966d4a0ae080f0b471e2d44297820428095e04ef44d746f11c88f4cc3f6a32361f8d3dffee11641790801569063b438517e018675297f3bd50e8dea007e5e58faeb7e6e11948710a011652dab7ff7fb3d743e0e4f776cc62d3a2c69652ca34bde7ba13ff26212702aba64ff917c12922dba4c42c4f5d203d9724708d0881ce2e5acf916fc7a6811f3433b8f60eddc97b9a584549a19ab4baf909939b427cb8cd91ec09cfde76280ec5cfc187a965b041421400e5d621f7c360a69379291aecac5edbb2a9cbf9688e7976fc2e8699f61e0c82731e5996efc6c1d342bfe9116b05417e2d743179897a378b93aa163af3638b4ef043686e856cf304fa9d5ea9e05f7104bf2396b771dc1d459ab017f2fb6772fb2f389608ae1d92c104bc70fc26b4f77e167ea48c950c1b7f54b407d9215d3b07d3b03935f1f86f5d347b1763a3332eee3cdd8b561175431bfb01a82d60366227ae3fb68e4e7c9ce1108010acc8c08c102b3220428302b428002b3220428302b428002b3220428302b428002b3220428302b428002b3220428302bb213e083661e1fe5cca498f5bc1fc909f0415ff283cbe83e3a913cf0ad65882417234cdd7209760a2b34f775c6c4ee75b855c7e9b874fc743a1959f9455834a431e6fd11055fa52d52546a7c3ca219dc1d6d306d4b3814569670b2b5c2c2214df895c0d91b7791aa2ac0532dbcb9058848526165482c5cecac31f3c940047838f2161d2bf65d45d25d35ea7b3960543b1fccdf19031f675b64e615c2cfd516f30735e6679666fbb964740854a28e9bb46f6e9764087e7b40037c1a1c87793bae704b09df85256125b1f76fe6895a4a3b0c6ce98d65bf47a2438092898f32b6a31f5685c6e1b51e01ec58cff7c713f159481c3fd2d1c2cf9908ca062b7f8bc4df97d3b8b584b99b2ee2cff0db78bd575d223807047a3860d58158b4aaed0255be0616937733311af3e66f11f8e35c0a3f922e9214605d77074cec561b396a0d7b18924a3c9da7af13c674f063c7a3dbd78233f1326ff42eb9d9a86b03774c26e2abe35edafb6411a11c89b9bfe4daacfe0dd0a8b107f6459616e0c5c42c34aaef86e789a06dad751ff594de75e1ed628b37882057108ffbce530df0feae28d66688d24e81433177f89174916c12e245bc526f228a33f177b98516122f4673e2b1687835c4e890616ae572432f47585959b062e486a848387fb567000e5f2d2dcee3b11978ae6d2de41694fc111468b4749deb3d7a357247586cc9ef48094f54613211e83f42808f2f85e48beed3c403bb49f8d3733959853624f41957c03255104b43ae3724f256365a936bdbd471c13f51a53d1d7dafc6de8e48c9285db8e84e7601fcdcec58b57d3d5aaa3e836ca41816b0a2f5040d38109d86916d7dc9b974617611b74a13c90a30bfb018cfb6f6c6de4b25023c762d039d025da136a8014d3114881e63d38e0bb7487fd10b039a79618fc16b52a850d4c4bb0e0af24170642ab782857f2785256bbf07f9d9f00f8086d9de4d4a575bb842c44ec3744322ea13d733b8559a48568034dc36f27642c2dd12af74333d9f240276f4bef15298bc51c8c81495920b1b12ab7b367447f095fb930dfa7e039b7b21344a1736b3f28a50d7c31e34f81a0ebfd0f72a207f00fb2edfc6cfa7127138fa0e960d2b7dab270df394bec483ef0d2f11b41491ac008bb8caacc99799c44558d277337449f7698d616c73b0d17d54b45f196122dba57dbb278860f6730f184ac2343db78078624368f5379af56e387c03ff77e8c67db780d2dfb5bea7ae0cdc532dbc885085001f4bf4759efb36f5c451127af348225087f4c728964623c2a606880d259a4b42290d897a6c3dedef899a422fa71eb095bf0b4e5dd725141712b2d0c4c749277a83172b2cd2c24f6987df2677c03f6f75c5a45e0118b8ea046fd55d47bb0994767594c4f366b39fa58a6405a87761034858a45ee95252163a06eab6d0ba4f80fcd99062838edbf1b8747cb835128a297b6037f54fa849780d25a1d310fdf94a7b052b4e9e53a04b1e98d3357803faa361163da28d2f0e1a64cf617119e8f5c931384ddf0b8fb7f6a1b0a018f1e979bc557a4857809cbe4d741ef032e9d807d5d609d0d8e351411a6882e1a8b0e63f01bb2fde86f6fba1285c3718f95f0cc2e7635b61d78564de4a3078bda75bf9602f39bf54a555530ae7d82bac5044bca77ea886ce9a68be7c06d9ab9f867aed603c455ecf38eb96129213a07e66511f826922909e53c8fa640e365624e3a5c320ace91e3431b8ad52f3231db98525c31ff9063b21517a3672235eb5c4031a0a7a30c994dfdb11c56656ee61286e72b2e121f59c1af2b026bf03cdc6e9f49c21bd1a93a4c7e0bda4866405c81349863ff9526dad754524599f9f9fa3a71d11cbf673254583a8589bd72a29b1abe2b329ba2df9b5e47c57dcb991c96c149a1dd30785f6392f9e4b66537d145abbd2ea9e42b550905fcc50ff91c9d970b6b5868db525f1d259f0e0d3817a8607f9b28c59aa484e8034f42566e462d3c904dce47da70ea453dfa3a13bfbf9eff034dc49cb6593fd7ad68f6d89295f9fc3acdf2e63eeb6484cd8780133fbd6636ddf1fbf716fdc4f17562d70295145d26b0bace2f3c2ab43e3f0070fc9747e19de0e70217d41caf67329f88d889b795e72ed17ff5c4772a61a1b8fdfc49a037168bbe410368c0b62e72edf77edde308e1e3a4e9846447a22569abb2e494e8094e5fb6231b15b00b69ed579b5493d02f0144946287f5d49c3ac679b6007114c36f76cf53c1da1dd341c451a2dec6dad707d495f66a71c884ac78b9dfdb186884ccfe23f633067740b9c215e9066a974850c5de1a2cf58b7bfde813dd301e57aa40b30a4b50fb99e082f4b8db8b43ccc20e2a6fdd24b492a242def8fd11d6be156663e6c89bbf4705220980fbdd079eb8331e9983dbc297e3c29cdbad3a2368cc0ac48d2030a1e1f84000566450850605684000566450850605684000566450850604680ff076dbb0ce971b0be980000000049454e44ae426082);

-- --------------------------------------------------------

--
-- Estrutura da tabela `situacao`
--

DROP TABLE IF EXISTS `situacao`;
CREATE TABLE IF NOT EXISTS `situacao` (
  `idsit` int(11) NOT NULL AUTO_INCREMENT,
  `situacao` varchar(50) NOT NULL,
  `idfilial` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idsit`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `situacao`
--

INSERT INTO `situacao` (`idsit`, `situacao`, `idfilial`) VALUES
(1, 'Selecione uma Situação', 1),
(2, 'Entrega Ok', 1),
(3, 'Orçamento Reprovado', 1),
(4, 'Aguardando Aprovação', 1),
(5, 'Aguardando Peças', 1),
(6, 'Abandonado Pelo Cliente', 1),
(7, 'Na Bancada', 1),
(8, 'Retornou', 1),
(10, 'teste novo', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbclientes`
--

DROP TABLE IF EXISTS `tbclientes`;
CREATE TABLE IF NOT EXISTS `tbclientes` (
  `idcli` int(11) NOT NULL AUTO_INCREMENT,
  `nomecli` varchar(50) NOT NULL,
  `endcli` varchar(100) DEFAULT NULL,
  `fonecli` varchar(50) NOT NULL,
  `emailcli` varchar(50) DEFAULT NULL,
  `cgc` varchar(16) DEFAULT NULL,
  `sitcli` varchar(8) NOT NULL DEFAULT 'Ativo',
  `idfilial` int(11) NOT NULL DEFAULT '1',
  `cidadecli` varchar(50) DEFAULT NULL,
  `bairrocli` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcli`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbclientes`
--

INSERT INTO `tbclientes` (`idcli`, `nomecli`, `endcli`, `fonecli`, `emailcli`, `cgc`, `sitcli`, `idfilial`, `cidadecli`, `bairrocli`) VALUES
(1, 'jose', 'rua a1', '99998888', 'jose@email.com', '12345678912', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(2, 'joao', 'rua b', '99998888', 'joao@joao.com', '12345678912', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(3, 'joaquim', 'rua c', '99997777', 'joaquim@email.com', '12345678912', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(16, 'jaqueline', 'rua d', '12344321', 'jack@email.com', '74568912111', 'Excluído', 1, 'itabaiana-se', 'OBDC'),
(5, 'lucas', 'rua l', '45611234', 'lucas@email.com', '74568912325', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(10, 'jjj', 'jsajojdaoj', '789456123', 'jjj@jjj.com', '45612378912', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(11, 'teste', 'obdc', '12345678', 'teste@teste.com', '', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(12, 'novoteste', 'obdc', '14253617', 'teste@obdc.com', '78945612301', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(13, 'joaquim', 'rua c', '99997777', 'joaquim@email.com', '12345678912', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(14, 'jaqueline', 'rua d', '12344321', 'jack@email.com', '74568912325', 'Excluído', 1, 'itabaiana-se', 'OBDC'),
(15, 'jaqueline', 'rua d', '12344321', 'jack@email.com', '74568912324', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(17, 'felipe', 'obdc', '132464856', 'felipe@obdc.com', '12345678123', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(20, 'alex distribuicao', 'obdc', '678123456', 'alex@obdc.com', '10123456000101', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(18, 'jaqueline', 'rua d', '12344321', 'jack@email.com', '74568912324', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(19, 'jaqueline', 'rua d', '12344321', 'jack@email.com', '74568912324', 'Ativo', 1, 'itabaiana-se', 'OBDC'),
(21, 'teste 2', 'OBDC,123', '999999999', 'teste@teste.com', '12234567890', 'Ativo', 1, 'ARACAJU', 'OBDC');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbos`
--

DROP TABLE IF EXISTS `tbos`;
CREATE TABLE IF NOT EXISTS `tbos` (
  `os` int(11) NOT NULL AUTO_INCREMENT,
  `dataos` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tipo` varchar(50) NOT NULL,
  `idsit` int(11) NOT NULL,
  `marca` varchar(150) NOT NULL,
  `modelo` varchar(100) NOT NULL,
  `cor` varchar(50) NOT NULL,
  `defeito` varchar(150) NOT NULL,
  `servico` varchar(150) DEFAULT NULL,
  `tecnico` varchar(35) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `imei1` varchar(20) DEFAULT NULL,
  `imei2` varchar(20) DEFAULT NULL,
  `excluido` varchar(8) NOT NULL DEFAULT 'Ativo',
  `idcli` int(11) NOT NULL,
  `datapagamento` timestamp NULL DEFAULT NULL,
  `formapagamento` varchar(30) DEFAULT NULL,
  `idfilial` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`os`),
  KEY `idcli` (`idcli`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbos`
--

INSERT INTO `tbos` (`os`, `dataos`, `tipo`, `idsit`, `marca`, `modelo`, `cor`, `defeito`, `servico`, `tecnico`, `valor`, `imei1`, `imei2`, `excluido`, `idcli`, `datapagamento`, `formapagamento`, `idfilial`) VALUES
(1, '2022-04-02 23:17:42', 'os', 8, 'motorola', 'moto g6', 'cinza', 'bateria descarrega rapidamente', 'troca de bateria', 'kedson', '90.00', '345678901234567', '345678901234546', 'Ativo', 1, '2022-04-26 23:43:36', 'Debito', 1),
(2, '2022-04-02 23:19:35', 'Orçamento', 5, 'moto ', 'moto g6', 'azul', 'vidro da tela', 'troca do vidro', 'kedson', '145.00', '', '', 'Ativo', 2, NULL, 'Em Aberto', 1),
(3, '2022-04-02 23:25:32', 'orçamento', 5, 'galaxy a10', '', '', 'não carrega', 'troca do conector', 'kedson', '40.00', '', '', 'Ativo', 4, NULL, 'Em Aberto', 1),
(4, '2022-04-02 23:28:41', 'os', 2, 'moto g8', '', '', 'tela quebrada', 'troca de frontal', 'kedson', '350.00', '', '', 'Ativo', 5, '2022-05-05 22:11:54', 'A Vista', 1),
(5, '2022-04-02 23:35:37', 'os', 3, 'all in one', '', '', 'lentidão', 'formatação', 'jose', '50.00', '', '', 'Ativo', 3, NULL, 'Em Aberto', 1),
(13, '2022-04-08 02:28:36', 'os', 5, 'telefone galaxy a03 core', 'a03 core', 'azul', 'não carrega', 'troca da placa de carga', 'joel', '10000.00', '', '', 'Ativo', 4, '2022-04-22 03:07:45', 'Pix', 1),
(14, '2022-04-08 02:44:27', 'os', 8, 'televisão', 'aoc', 'preta', 'não liga', 'traca da placa de carga', 'josa', '450.00', '', '', 'Ativo', 10, '2022-04-20 23:39:11', 'Cartão 12x', 1),
(21, '2022-04-15 16:04:11', 'orçamento', 6, 'samsung', 'a10', 'azul', 'não liga', '', '', '0.00', '', '', 'Ativo', 20, NULL, 'Em Aberto', 1),
(22, '2022-04-15 16:06:24', 'orçamento', 10, 'samsung', 'a01', 'preto', 'tela quebrada', '', '', '0.00', '', '', 'Ativo', 20, NULL, 'Em Aberto', 1),
(23, '2022-04-15 17:16:56', 'Orçamento', 5, 'kjhsadkhjsadkhj', 'lkjdsfjlksdfjkldsf', 'KLJDFJKL', 'JLKJLKÇFDLKÇDFLÇKDF', 'LKÇJFDKLÇFDKLÇF', 'KLÇFLKÇFDKLÇFD', '0.00', '57437328762387', '76376843894398', 'Ativo', 20, NULL, 'Em Aberto', 1),
(24, '2022-04-15 22:19:38', 'Orçamento', 10, 'motorola', 'moto g6', 'preto', 'não liga', 'troca do batão power / bateria', 'Kedson', '140.00', '56776788787878788', '671267687187687781', 'Ativo', 17, NULL, 'Em Aberto', 1),
(25, '2022-05-08 17:05:19', 'Orçamento', 10, 'SAMSUNG', 'GALAXY S20', 'PRETO', 'tela quebrada', '', 'KEDSON', '0.00', '126543658598092', '3876467846783768', 'Ativo', 12, NULL, NULL, 1),
(26, '2022-05-08 18:11:40', 'Orçamento', 4, 'XIAOMI', 'REDMI 9T', 'PRETO', 'Não liga', 'Conserto na placa', 'KEDSON', '150.00', '12345678999', '1234576832256', 'Ativo', 18, NULL, NULL, 1),
(27, '2022-05-08 18:23:13', 'Orçamento', 8, 'XIAOMI', 'REDMI 11 PRO', 'prata', 'NÃO CARREGA', 'TROCA DO CONECTOR', 'KEDSON', '90.00', '234567890123456', '234567892345234', 'Ativo', 5, NULL, NULL, 1),
(28, '2022-05-09 01:19:58', 'orçamento', 7, 'SAMSUNG', 'GALAXY A01', 'GRAFITE', 'NÃO LIGA', 'TROCA DA PLACA', 'KEDSON', '200.00', '234567123456123', '234561234123678', 'Ativo', 17, NULL, 'Em Aberto', 1),
(29, '2022-05-09 01:22:14', 'os', 10, 'abcde', 'fghij', 'wxyz', 'klmn', 'opqr', 'stu', '0.00', '', '', 'Ativo', 12, NULL, 'Em Aberto', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbusuarios`
--

DROP TABLE IF EXISTS `tbusuarios`;
CREATE TABLE IF NOT EXISTS `tbusuarios` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `fone` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `login` varchar(15) COLLATE latin1_general_ci NOT NULL,
  `senha` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `perfil` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `excluido` varchar(8) COLLATE latin1_general_ci NOT NULL DEFAULT 'Ativo',
  `idfilial` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `login` (`login`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Extraindo dados da tabela `tbusuarios`
--

INSERT INTO `tbusuarios` (`iduser`, `usuario`, `fone`, `login`, `senha`, `perfil`, `excluido`, `idfilial`) VALUES
(1, 'administrador', '9999-9999', 'admin', 'pº×ÙŒ’ñpCf4Í·3', 'Administrador', 'Não', 1),
(2, 'usuario', '9999-9999', 'user', 'œ%øw¿uÃ\nŸ	Ap', 'Usuario', 'Não', 1),
(8, 'ad8', '122345678', 'ad8', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Administrador', 'Sim', 1),
(5, 'ad', '789456123', 'ad1', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Administrador', 'Não', 1),
(6, 'ad2', '789456123', 'ad2', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Administrador', 'Não', 1),
(7, 'ad3', '', 'ad3', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Administrador', 'Não', 1),
(9, 'teste', '99999999', 'teste', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Usuario', 'Não', 1),
(10, 'teste', '', 'teste2', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Administrador', 'Não', 1),
(11, 'teste3', '78955466', 'teste3', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Administrador', 'Sim', 1),
(12, 'teste4', '6798090909', 'teste4', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Usuario', 'Sim', 1),
(13, 'teste5', '14253670', 'teste5', 'ÅruÔþýfƒ\'êÔKçô\\î', 'Usuario', 'Sim', 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
