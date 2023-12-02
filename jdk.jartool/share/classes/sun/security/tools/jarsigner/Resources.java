package sun.security.tools.jarsigner;

/**
 * <p> This class represents the <code>ResourceBundle</code>
 * for JarSigner.
 *
 */
public class Resources extends java.util.ListResourceBundle {

    private static final Object[][] contents = {

        // shared (from jarsigner)
        {"SPACE", " "},
        {"6SPACE", "      "},
        {"COMMA", ", "},

        {"provclass.not.a.provider", "%s not a provider"},
        {"provider.name.not.found", "Provider named \"%s\" not found"},
        {"provider.class.not.found", "Provider \"%s\" not found"},
        {"jarsigner.error.", "jarsigner error: "},
        {"Illegal.option.", "Illegal option: "},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-keystore must be NONE if -storetype is {0}"},
        {".keypass.can.not.be.specified.if.storetype.is.{0}",
                "-keypass can not be specified if -storetype is {0}"},
        {"If.protected.is.specified.then.storepass.and.keypass.must.not.be.specified",
                "If -protected is specified, then -storepass and -keypass must not be specified"},
        {"If.keystore.is.not.password.protected.then.storepass.and.keypass.must.not.be.specified",
                 "If keystore is not password protected, then -storepass and -keypass must not be specified"},
        {"Usage.jarsigner.options.jar.file.alias",
                "Usage: jarsigner [options] jar-file alias"},
        {".jarsigner.verify.options.jar.file.alias.",
                "       jarsigner -verify [options] jar-file [alias...]"},
        {".jarsigner.version",
                "       jarsigner -version"},
        {".keystore.url.keystore.location",
                "[-keystore <url>]           keystore location"},
        {".storepass.password.password.for.keystore.integrity",
            "[-storepass <password>]     password for keystore integrity"},
        {".storetype.type.keystore.type",
                "[-storetype <type>]         keystore type"},
        {".keypass.password.password.for.private.key.if.different.",
                "[-keypass <password>]       password for private key (if different)"},
        {".certchain.file.name.of.alternative.certchain.file",
                "[-certchain <file>]         name of alternative certchain file"},
        {".sigfile.file.name.of.SF.DSA.file",
                "[-sigfile <file>]           name of .SF/.DSA file"},
        {".signedjar.file.name.of.signed.JAR.file",
                "[-signedjar <file>]         name of signed JAR file"},
        {".digestalg.algorithm.name.of.digest.algorithm",
                "[-digestalg <algorithm>]    name of digest algorithm"},
        {".sigalg.algorithm.name.of.signature.algorithm",
                "[-sigalg <algorithm>]       name of signature algorithm"},
        {".verify.verify.a.signed.JAR.file",
                "[-verify]                   verify a signed JAR file"},
        {".version.print.the.program.version",
                "[-version]                  print the program version"},
        {".verbose.suboptions.verbose.output.when.signing.verifying.",
                "[-verbose[:suboptions]]     verbose output when signing/verifying."},
        {".suboptions.can.be.all.grouped.or.summary",
                "                            suboptions can be all, grouped or summary"},
        {".certs.display.certificates.when.verbose.and.verifying",
                "[-certs]                    display certificates when verbose and verifying"},
        {".certs.revocation.check",
                "[-revCheck]                 Enable certificate revocation check"},
        {".tsa.url.location.of.the.Timestamping.Authority",
                "[-tsa <url>]                location of the Timestamping Authority"},
        {".tsacert.alias.public.key.certificate.for.Timestamping.Authority",
                "[-tsacert <alias>]          public key certificate for Timestamping Authority"},
        {".tsapolicyid.tsapolicyid.for.Timestamping.Authority",
                "[-tsapolicyid <oid>]        TSAPolicyID for Timestamping Authority"},
        {".tsadigestalg.algorithm.of.digest.data.in.timestamping.request",
                "[-tsadigestalg <algorithm>] algorithm of digest data in timestamping request"},
        {".internalsf.include.the.SF.file.inside.the.signature.block",
                "[-internalsf]               include the .SF file inside the signature block"},
        {".sectionsonly.don.t.compute.hash.of.entire.manifest",
                "[-sectionsonly]             don't compute hash of entire manifest"},
        {".protected.keystore.has.protected.authentication.path",
                "[-protected]                keystore has protected authentication path"},
        {".providerName.name.provider.name",
                "[-providerName <name>]      provider name"},
        {".add.provider.option",
                "[-addprovider <name>        add security provider by name (e.g. SunPKCS11)"},
        {".providerArg.option.1",
                "  [-providerArg <arg>]] ... configure argument for -addprovider"},
        {".providerClass.option",
                "[-providerClass <class>     add security provider by fully-qualified class name"},
        {".providerArg.option.2",
                "  [-providerArg <arg>]] ... configure argument for -providerClass"},
        {".providerPath.option",
                "[-providerPath <list>]      provider classpath"},
        {".strict.treat.warnings.as.errors",
                "[-strict]                   treat warnings as errors"},
        {".conf.url.specify.a.pre.configured.options.file",
                "[-conf <url>]               specify a pre-configured options file"},
        {".print.this.help.message",
                "[-? -h --help]              Print this help message"},
        {"Option.lacks.argument", "Option lacks argument"},
        {"Please.type.jarsigner.help.for.usage", "Please type jarsigner --help for usage"},
        {"Please.specify.jarfile.name", "Please specify jarfile name"},
        {"Please.specify.alias.name", "Please specify alias name"},
        {"Only.one.alias.can.be.specified", "Only one alias can be specified"},
        {"This.jar.contains.signed.entries.which.is.not.signed.by.the.specified.alias.es.",
                 "This jar contains signed entries which are not signed by the specified alias(es)."},
        {"This.jar.contains.signed.entries.that.s.not.signed.by.alias.in.this.keystore.",
                  "This jar contains signed entries that are not signed by alias in this keystore."},
        {"s", "s"},
        {"m", "m"},
        {"k", "k"},
        {"X", "X"},
        {"q", "?"},
        {".and.d.more.", "(and %d more)"},
        {".s.signature.was.verified.",
                "  s = signature was verified "},
        {".m.entry.is.listed.in.manifest",
                "  m = entry is listed in manifest"},
        {".k.at.least.one.certificate.was.found.in.keystore",
                "  k = at least one certificate was found in keystore"},
        {".X.not.signed.by.specified.alias.es.",
                "  X = not signed by specified alias(es)"},
        {".q.unsigned.entry",
                "  ? = unsigned entry"},
        {"no.manifest.", "no manifest."},
        {".Signature.related.entries.","(Signature related entries)"},
        {".Unsigned.entries.", "(Unsigned entries)"},
        {".Directory.entries.", "(Directory entries)"},
        {"jar.is.unsigned",
                "jar is unsigned."},
        {"jar.treated.unsigned",
                "WARNING: Signature is either not parsable or not verifiable, and the jar will be treated as unsigned. For more information, re-run jarsigner with debug enabled (-J-Djava.security.debug=jar)."},
        {"jar.treated.unsigned.see.weak",
                "The jar will be treated as unsigned, because it is signed with a weak algorithm that is now disabled.\n\nRe-run jarsigner with the -verbose option for more details."},
        {"jar.treated.unsigned.see.weak.verbose",
                "WARNING: The jar will be treated as unsigned, because it is signed with a weak algorithm that is now disabled by the security property:"},
        {"jar.signed.", "jar signed."},
        {"jar.signed.with.signer.errors.", "jar signed, with signer errors."},
        {"jar.verified.", "jar verified."},
        {"jar.verified.with.signer.errors.", "jar verified, with signer errors."},

        {"history.with.ts", "- Signed by \"%1$s\"\n    Digest algorithm: %2$s\n    Signature algorithm: %3$s, %4$s\n  Timestamped by \"%6$s\" on %5$tc\n    Timestamp digest algorithm: %7$s\n    Timestamp signature algorithm: %8$s, %9$s"},
        {"history.without.ts", "- Signed by \"%1$s\"\n    Digest algorithm: %2$s\n    Signature algorithm: %3$s, %4$s"},
        {"history.unparsable", "- Unparsable signature-related file %s"},
        {"history.nosf", "- Missing signature-related file META-INF/%s.SF"},
        {"history.nobk", "- Missing block file for signature-related file META-INF/%s.SF"},

        {"with.weak", "%s (weak)"},
        {"with.algparams.weak", "%1$s using %2$s (weak)"},
        {"with.disabled", "%s (disabled)"},
        {"with.algparams.disabled", "%1$s using %2$s (disabled)"},
        {"key.bit", "%d-bit key"},
        {"key.bit.weak", "%d-bit key (weak)"},
        {"key.bit.eccurve.weak", "%1$d-bit %2$s key (weak)"},
        {"key.bit.disabled", "%d-bit key (disabled)"},
        {"key.bit.eccurve.disabled", "%1$d-bit %2$s key (disabled)"},
        {"unknown.size", "unknown size"},
        {"extra.attributes.detected", "POSIX file permission and/or symlink attributes detected. These attributes are ignored when signing and are not protected by the signature."},

        {"jarsigner.", "jarsigner: "},
        {"signature.filename.must.consist.of.the.following.characters.A.Z.0.9.or.",
                "signature filename must consist of the following characters: A-Z, 0-9, _ or -"},
        {"unable.to.open.jar.file.", "unable to open jar file: "},
        {"unable.to.create.", "unable to create: "},
        {".adding.", "   adding: "},
        {".updating.", " updating: "},
        {".signing.", "  signing: "},
        {"attempt.to.rename.signedJarFile.to.jarFile.failed",
                "attempt to rename {0} to {1} failed"},
        {"attempt.to.rename.jarFile.to.origJar.failed",
                "attempt to rename {0} to {1} failed"},
        {"unable.to.sign.jar.", "unable to sign jar: "},
        {"Enter.Passphrase.for.keystore.", "Enter Passphrase for keystore: "},
        {"keystore.load.", "keystore load: "},
        {"certificate.exception.", "certificate exception: "},
        {"unable.to.instantiate.keystore.class.",
                "unable to instantiate keystore class: "},
        {"Certificate.chain.not.found.for.alias.alias.must.reference.a.valid.KeyStore.key.entry.containing.a.private.key.and",
                "Certificate chain not found for: {0}.  {1} must reference a valid KeyStore key entry containing a private key and corresponding public key certificate chain."},
        {"File.specified.by.certchain.does.not.exist",
                "File specified by -certchain does not exist"},
        {"Cannot.restore.certchain.from.file.specified",
                "Cannot restore certchain from file specified"},
        {"Certificate.chain.not.found.in.the.file.specified.",
                "Certificate chain not found in the file specified."},
        {"found.non.X.509.certificate.in.signer.s.chain",
                "found non-X.509 certificate in signer's chain"},
        {"Enter.key.password.for.alias.", "Enter key password for {0}: "},
        {"unable.to.recover.key.from.keystore",
                "unable to recover key from keystore"},
        {"key.associated.with.alias.not.a.private.key",
                "key associated with {0} not a private key"},
        {"you.must.enter.key.password", "you must enter key password"},
        {"unable.to.read.password.", "unable to read password: "},
        {"certificate.is.valid.from", "certificate is valid from {0} to {1}"},
        {"certificate.expired.on", "certificate expired on {0}"},
        {"certificate.is.not.valid.until",
                "certificate is not valid until {0}"},
        {"certificate.will.expire.on", "certificate will expire on {0}"},
        {".Invalid.certificate.chain.", "[Invalid certificate chain: "},
        {".Invalid.TSA.certificate.chain.", "[Invalid TSA certificate chain: "},
        {"requesting.a.signature.timestamp",
                "requesting a signature timestamp"},
        {"TSA.location.", "TSA location: "},
        {"TSA.certificate.", "TSA certificate: "},
        {"no.response.from.the.Timestamping.Authority.",
                "no response from the Timestamping Authority. When connecting"
                + " from behind a firewall an HTTP or HTTPS proxy may need to"
                + " be specified. Supply the following options to jarsigner:"},
        {"or", "or"},
        {"Certificate.not.found.for.alias.alias.must.reference.a.valid.KeyStore.entry.containing.an.X.509.public.key.certificate.for.the",
                "Certificate not found for: {0}.  {1} must reference a valid KeyStore entry containing an X.509 public key certificate for the Timestamping Authority."},
        {"entry.was.signed.on", "entry was signed on {0}"},
        {"Warning.", "Warning: "},
        {"Error.", "Error: "},
        {"...Signer", ">>> Signer"},
        {"...TSA", ">>> TSA"},
        {"trusted.certificate", "trusted certificate"},
        {"This.jar.contains.unsigned.entries.which.have.not.been.integrity.checked.",
                "This jar contains unsigned entries which have not been integrity-checked. "},
        {"This.jar.contains.entries.whose.signer.certificate.has.expired.",
                "This jar contains entries whose signer certificate has expired. "},
        {"This.jar.contains.entries.whose.signer.certificate.will.expire.within.six.months.",
                "This jar contains entries whose signer certificate will expire within six months. "},
        {"This.jar.contains.entries.whose.signer.certificate.is.not.yet.valid.",
                "This jar contains entries whose signer certificate is not yet valid. "},
        {"This.jar.contains.entries.whose.signer.certificate.is.self.signed.",
                "This jar contains entries whose signer certificate is self-signed."},
        {"Re.run.with.the.verbose.and.certs.options.for.more.details.",
                "Re-run with the -verbose and -certs options for more details."},
        {"The.signer.certificate.has.expired.",
                "The signer certificate has expired."},
        {"The.timestamp.expired.1.but.usable.2",
                "The timestamp expired on %1$tY-%1$tm-%1$td. However, the JAR will be valid until the signer certificate expires on %2$tY-%2$tm-%2$td."},
        {"The.timestamp.has.expired.",
                "The timestamp has expired."},
        {"The.signer.certificate.will.expire.within.six.months.",
                "The signer certificate will expire within six months."},
        {"The.timestamp.will.expire.within.one.year.on.1",
                "The timestamp will expire within one year on %1$tY-%1$tm-%1$td."},
        {"The.timestamp.will.expire.within.one.year.on.1.but.2",
                "The timestamp will expire within one year on %1$tY-%1$tm-%1$td. However, the JAR will be valid until the signer certificate expires on %2$tY-%2$tm-%2$td."},
        {"The.signer.certificate.is.not.yet.valid.",
                "The signer certificate is not yet valid."},
        {"The.signer.certificate.s.KeyUsage.extension.doesn.t.allow.code.signing.",
                 "The signer certificate's KeyUsage extension doesn't allow code signing."},
        {"The.signer.certificate.s.ExtendedKeyUsage.extension.doesn.t.allow.code.signing.",
                 "The signer certificate's ExtendedKeyUsage extension doesn't allow code signing."},
        {"The.signer.certificate.s.NetscapeCertType.extension.doesn.t.allow.code.signing.",
                 "The signer certificate's NetscapeCertType extension doesn't allow code signing."},
        {"This.jar.contains.entries.whose.signer.certificate.s.KeyUsage.extension.doesn.t.allow.code.signing.",
                 "This jar contains entries whose signer certificate's KeyUsage extension doesn't allow code signing."},
        {"This.jar.contains.entries.whose.signer.certificate.s.ExtendedKeyUsage.extension.doesn.t.allow.code.signing.",
                 "This jar contains entries whose signer certificate's ExtendedKeyUsage extension doesn't allow code signing."},
        {"This.jar.contains.entries.whose.signer.certificate.s.NetscapeCertType.extension.doesn.t.allow.code.signing.",
                 "This jar contains entries whose signer certificate's NetscapeCertType extension doesn't allow code signing."},
        {".{0}.extension.does.not.support.code.signing.",
                 "[{0} extension does not support code signing]"},
        {"The.signer.s.certificate.chain.is.invalid.reason.1",
                "The signer's certificate chain is invalid. Reason: %s"},
        {"The.tsa.certificate.chain.is.invalid.reason.1",
                "The TSA certificate chain is invalid. Reason: %s"},
        {"The.signer.s.certificate.is.self.signed.",
                "The signer's certificate is self-signed."},
        {"The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update.",
                "The %1$s algorithm specified for the %2$s option is considered a security risk. This algorithm will be disabled in a future update."},
        {"The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk.and.is.disabled.",
                "The %1$s algorithm specified for the %2$s option is considered a security risk and is disabled."},
        {"The.timestamp.digest.algorithm.1.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update.",
                "The %1$s timestamp digest algorithm is considered a security risk. This algorithm will be disabled in a future update."},
        {"The.digest.algorithm.1.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update.",
                "The %1$s digest algorithm is considered a security risk. This algorithm will be disabled in a future update."},
        {"The.signature.algorithm.1.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update.",
                "The %1$s signature algorithm is considered a security risk. This algorithm will be disabled in a future update."},
        {"The.1.signing.key.has.a.keysize.of.2.which.is.considered.a.security.risk..This.key.size.will.be.disabled.in.a.future.update.",
                "The %1$s signing key has a keysize of %2$d which is considered a security risk. This key size will be disabled in a future update."},
        {"The.1.signing.key.has.a.keysize.of.2.which.is.considered.a.security.risk.and.is.disabled.",
                "The %1$s signing key has a keysize of %2$d which is considered a security risk and is disabled."},
        {"This.jar.contains.entries.whose.certificate.chain.is.invalid.reason.1",
                 "This jar contains entries whose certificate chain is invalid. Reason: %s"},
        {"This.jar.contains.entries.whose.tsa.certificate.chain.is.invalid.reason.1",
                "This jar contains entries whose TSA certificate chain is invalid. Reason: %s"},
        {"no.timestamp.signing",
                "No -tsa or -tsacert is provided and this jar is not timestamped. Without a timestamp, users may not be able to validate this jar after the signer certificate's expiration date (%1$tY-%1$tm-%1$td)."},
        {"invalid.timestamp.signing",
                "The timestamp is invalid. Without a valid timestamp, users may not be able to validate this jar after the signer certificate's expiration date (%1$tY-%1$tm-%1$td)."},
        {"no.timestamp.verifying",
                "This jar contains signatures that do not include a timestamp. Without a timestamp, users may not be able to validate this jar after any of the signer certificates expire (as early as %1$tY-%1$tm-%1$td)."},
        {"bad.timestamp.verifying",
                "This jar contains signatures that include an invalid timestamp. Without a valid timestamp, users may not be able to validate this jar after any of the signer certificates expire (as early as %1$tY-%1$tm-%1$td).\nRerun jarsigner with -J-Djava.security.debug=jar for more information."},
        {"The.signer.certificate.will.expire.on.1.",
                "The signer certificate will expire on %1$tY-%1$tm-%1$td."},
        {"The.timestamp.will.expire.on.1.",
                "The timestamp will expire on %1$tY-%1$tm-%1$td."},
        {"signer.cert.expired.1.but.timestamp.good.2.",
                "The signer certificate expired on %1$tY-%1$tm-%1$td. However, the JAR will be valid until the timestamp expires on %2$tY-%2$tm-%2$td."},
        {"Unknown.password.type.", "Unknown password type: "},
        {"Cannot.find.environment.variable.",
                "Cannot find environment variable: "},
        {"Cannot.find.file.", "Cannot find file: "},
        {"event.ocsp.check", "Contacting OCSP server at %s ..."},
        {"event.crl.check", "Downloading CRL from %s ..."},
    };

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
