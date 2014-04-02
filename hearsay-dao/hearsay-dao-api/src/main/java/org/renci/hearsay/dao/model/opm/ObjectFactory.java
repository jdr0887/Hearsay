package org.renci.hearsay.dao.model.opm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * org.renci.hearsay.dao.model.opm package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content.
 * The Java representation of XML content can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory methods for each of these are provided in
 * this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HadPrimarySource_QNAME = new QName("http://www.w3.org/ns/prov#", "hadPrimarySource");

    private final static QName _Collection_QNAME = new QName("http://www.w3.org/ns/prov#", "collection");

    private final static QName _WasDerivedFrom_QNAME = new QName("http://www.w3.org/ns/prov#", "wasDerivedFrom");

    private final static QName _Used_QNAME = new QName("http://www.w3.org/ns/prov#", "used");

    private final static QName _Type_QNAME = new QName("http://www.w3.org/ns/prov#", "type");

    private final static QName _WasInformedBy_QNAME = new QName("http://www.w3.org/ns/prov#", "wasInformedBy");

    private final static QName _ActedOnBehalfOf_QNAME = new QName("http://www.w3.org/ns/prov#", "actedOnBehalfOf");

    private final static QName _EmptyDictionary_QNAME = new QName("http://www.w3.org/ns/prov#", "emptyDictionary");

    private final static QName _WasAssociatedWith_QNAME = new QName("http://www.w3.org/ns/prov#", "wasAssociatedWith");

    private final static QName _SoftwareAgent_QNAME = new QName("http://www.w3.org/ns/prov#", "softwareAgent");

    private final static QName _Other_QNAME = new QName("http://www.w3.org/ns/prov#", "other");

    private final static QName _Person_QNAME = new QName("http://www.w3.org/ns/prov#", "person");

    private final static QName _WasRevisionOf_QNAME = new QName("http://www.w3.org/ns/prov#", "wasRevisionOf");

    private final static QName _Location_QNAME = new QName("http://www.w3.org/ns/prov#", "location");

    private final static QName _InternalElement_QNAME = new QName("http://www.w3.org/ns/prov#", "internalElement");

    private final static QName _WasInfluencedBy_QNAME = new QName("http://www.w3.org/ns/prov#", "wasInfluencedBy");

    private final static QName _Entity_QNAME = new QName("http://www.w3.org/ns/prov#", "entity");

    private final static QName _EmptyCollection_QNAME = new QName("http://www.w3.org/ns/prov#", "emptyCollection");

    private final static QName _Role_QNAME = new QName("http://www.w3.org/ns/prov#", "role");

    private final static QName _Bundle_QNAME = new QName("http://www.w3.org/ns/prov#", "bundle");

    private final static QName _Value_QNAME = new QName("http://www.w3.org/ns/prov#", "value");

    private final static QName _AlternateOf_QNAME = new QName("http://www.w3.org/ns/prov#", "alternateOf");

    private final static QName _Activity_QNAME = new QName("http://www.w3.org/ns/prov#", "activity");

    private final static QName _HadDictionaryMember_QNAME = new QName("http://www.w3.org/ns/prov#",
            "hadDictionaryMember");

    private final static QName _WasEndedBy_QNAME = new QName("http://www.w3.org/ns/prov#", "wasEndedBy");

    private final static QName _Document_QNAME = new QName("http://www.w3.org/ns/prov#", "document");

    private final static QName _Organization_QNAME = new QName("http://www.w3.org/ns/prov#", "organization");

    private final static QName _HadMember_QNAME = new QName("http://www.w3.org/ns/prov#", "hadMember");

    private final static QName _Dictionary_QNAME = new QName("http://www.w3.org/ns/prov#", "dictionary");

    private final static QName _Label_QNAME = new QName("http://www.w3.org/ns/prov#", "label");

    private final static QName _Agent_QNAME = new QName("http://www.w3.org/ns/prov#", "agent");

    private final static QName _WasInvalidatedBy_QNAME = new QName("http://www.w3.org/ns/prov#", "wasInvalidatedBy");

    private final static QName _MentionOf_QNAME = new QName("http://www.w3.org/ns/prov#", "mentionOf");

    private final static QName _WasAttributedTo_QNAME = new QName("http://www.w3.org/ns/prov#", "wasAttributedTo");

    private final static QName _SpecializationOf_QNAME = new QName("http://www.w3.org/ns/prov#", "specializationOf");

    private final static QName _WasStartedBy_QNAME = new QName("http://www.w3.org/ns/prov#", "wasStartedBy");

    private final static QName _DerivedByRemovalFrom_QNAME = new QName("http://www.w3.org/ns/prov#",
            "derivedByRemovalFrom");

    private final static QName _DerivedByInsertionFrom_QNAME = new QName("http://www.w3.org/ns/prov#",
            "derivedByInsertionFrom");

    private final static QName _WasQuotedFrom_QNAME = new QName("http://www.w3.org/ns/prov#", "wasQuotedFrom");

    private final static QName _KeyEntityPair_QNAME = new QName("http://www.w3.org/ns/prov#", "keyEntityPair");

    private final static QName _Plan_QNAME = new QName("http://www.w3.org/ns/prov#", "plan");

    private final static QName _WasGeneratedBy_QNAME = new QName("http://www.w3.org/ns/prov#", "wasGeneratedBy");

    private final static QName _DocumentBundleContent_QNAME = new QName("http://www.w3.org/ns/prov#", "bundleContent");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * org.renci.hearsay.dao.model.opm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmptyDictionary }
     * 
     */
    public EmptyDictionary createEmptyDictionary() {
        return new EmptyDictionary();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link Other }
     * 
     */
    public Other createOther() {
        return new Other();
    }

    /**
     * Create an instance of {@link SoftwareAgent }
     * 
     */
    public SoftwareAgent createSoftwareAgent() {
        return new SoftwareAgent();
    }

    /**
     * Create an instance of {@link Association }
     * 
     */
    public Association createAssociation() {
        return new Association();
    }

    /**
     * Create an instance of {@link Influence }
     * 
     */
    public Influence createInfluence() {
        return new Influence();
    }

    /**
     * Create an instance of {@link Revision }
     * 
     */
    public Revision createRevision() {
        return new Revision();
    }

    /**
     * Create an instance of {@link EmptyCollection }
     * 
     */
    public EmptyCollection createEmptyCollection() {
        return new EmptyCollection();
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link PrimarySource }
     * 
     */
    public PrimarySource createPrimarySource() {
        return new PrimarySource();
    }

    /**
     * Create an instance of {@link Collection }
     * 
     */
    public Collection createCollection() {
        return new Collection();
    }

    /**
     * Create an instance of {@link Derivation }
     * 
     */
    public Derivation createDerivation() {
        return new Derivation();
    }

    /**
     * Create an instance of {@link Usage }
     * 
     */
    public Usage createUsage() {
        return new Usage();
    }

    /**
     * Create an instance of {@link Delegation }
     * 
     */
    public Delegation createDelegation() {
        return new Delegation();
    }

    /**
     * Create an instance of {@link Communication }
     * 
     */
    public Communication createCommunication() {
        return new Communication();
    }

    /**
     * Create an instance of {@link DictionaryMembership }
     * 
     */
    public DictionaryMembership createDictionaryMembership() {
        return new DictionaryMembership();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link Organization }
     * 
     */
    public Organization createOrganization() {
        return new Organization();
    }

    /**
     * Create an instance of {@link End }
     * 
     */
    public End createEnd() {
        return new End();
    }

    /**
     * Create an instance of {@link Membership }
     * 
     */
    public Membership createMembership() {
        return new Membership();
    }

    /**
     * Create an instance of {@link Bundle }
     * 
     */
    public Bundle createBundle() {
        return new Bundle();
    }

    /**
     * Create an instance of {@link Alternate }
     * 
     */
    public Alternate createAlternate() {
        return new Alternate();
    }

    /**
     * Create an instance of {@link Activity }
     * 
     */
    public Activity createActivity() {
        return new Activity();
    }

    /**
     * Create an instance of {@link Specialization }
     * 
     */
    public Specialization createSpecialization() {
        return new Specialization();
    }

    /**
     * Create an instance of {@link Attribution }
     * 
     */
    public Attribution createAttribution() {
        return new Attribution();
    }

    /**
     * Create an instance of {@link InternationalizedString }
     * 
     */
    public InternationalizedString createInternationalizedString() {
        return new InternationalizedString();
    }

    /**
     * Create an instance of {@link Dictionary }
     * 
     */
    public Dictionary createDictionary() {
        return new Dictionary();
    }

    /**
     * Create an instance of {@link Mention }
     * 
     */
    public Mention createMention() {
        return new Mention();
    }

    /**
     * Create an instance of {@link Invalidation }
     * 
     */
    public Invalidation createInvalidation() {
        return new Invalidation();
    }

    /**
     * Create an instance of {@link Agent }
     * 
     */
    public Agent createAgent() {
        return new Agent();
    }

    /**
     * Create an instance of {@link Insertion }
     * 
     */
    public Insertion createInsertion() {
        return new Insertion();
    }

    /**
     * Create an instance of {@link Removal }
     * 
     */
    public Removal createRemoval() {
        return new Removal();
    }

    /**
     * Create an instance of {@link Plan }
     * 
     */
    public Plan createPlan() {
        return new Plan();
    }

    /**
     * Create an instance of {@link KeyEntityPair }
     * 
     */
    public KeyEntityPair createKeyEntityPair() {
        return new KeyEntityPair();
    }

    /**
     * Create an instance of {@link Quotation }
     * 
     */
    public Quotation createQuotation() {
        return new Quotation();
    }

    /**
     * Create an instance of {@link Generation }
     * 
     */
    public Generation createGeneration() {
        return new Generation();
    }

    /**
     * Create an instance of {@link Start }
     * 
     */
    public Start createStart() {
        return new Start();
    }

    /**
     * Create an instance of {@link BundleConstructor }
     * 
     */
    public BundleConstructor createBundleConstructor() {
        return new BundleConstructor();
    }

    /**
     * Create an instance of {@link IDRef }
     * 
     */
    public IDRef createIDRef() {
        return new IDRef();
    }

    public Type createType() {
        return new Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimarySource }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "hadPrimarySource")
    public JAXBElement<PrimarySource> createHadPrimarySource(PrimarySource value) {
        return new JAXBElement<PrimarySource>(_HadPrimarySource_QNAME, PrimarySource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Collection }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "collection")
    public JAXBElement<Collection> createCollection(Collection value) {
        return new JAXBElement<Collection>(_Collection_QNAME, Collection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Derivation }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasDerivedFrom")
    public JAXBElement<Derivation> createWasDerivedFrom(Derivation value) {
        return new JAXBElement<Derivation>(_WasDerivedFrom_QNAME, Derivation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Usage }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "used")
    public JAXBElement<Usage> createUsed(Usage value) {
        return new JAXBElement<Usage>(_Used_QNAME, Usage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "type")
    public JAXBElement<Type> createType(Type value) {
        return new JAXBElement<Type>(_Type_QNAME, Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Communication }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasInformedBy")
    public JAXBElement<Communication> createWasInformedBy(Communication value) {
        return new JAXBElement<Communication>(_WasInformedBy_QNAME, Communication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delegation }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "actedOnBehalfOf")
    public JAXBElement<Delegation> createActedOnBehalfOf(Delegation value) {
        return new JAXBElement<Delegation>(_ActedOnBehalfOf_QNAME, Delegation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyDictionary }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "emptyDictionary", substitutionHeadNamespace = "http://www.w3.org/ns/prov#", substitutionHeadName = "internalElement")
    public JAXBElement<EmptyDictionary> createEmptyDictionary(EmptyDictionary value) {
        return new JAXBElement<EmptyDictionary>(_EmptyDictionary_QNAME, EmptyDictionary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Association }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasAssociatedWith")
    public JAXBElement<Association> createWasAssociatedWith(Association value) {
        return new JAXBElement<Association>(_WasAssociatedWith_QNAME, Association.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoftwareAgent }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "softwareAgent")
    public JAXBElement<SoftwareAgent> createSoftwareAgent(SoftwareAgent value) {
        return new JAXBElement<SoftwareAgent>(_SoftwareAgent_QNAME, SoftwareAgent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Other }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "other")
    public JAXBElement<Other> createOther(Other value) {
        return new JAXBElement<Other>(_Other_QNAME, Other.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Revision }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasRevisionOf")
    public JAXBElement<Revision> createWasRevisionOf(Revision value) {
        return new JAXBElement<Revision>(_WasRevisionOf_QNAME, Revision.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "location")
    public JAXBElement<Object> createLocation(Object value) {
        return new JAXBElement<Object>(_Location_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "internalElement")
    public JAXBElement<Object> createInternalElement(Object value) {
        return new JAXBElement<Object>(_InternalElement_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Influence }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasInfluencedBy")
    public JAXBElement<Influence> createWasInfluencedBy(Influence value) {
        return new JAXBElement<Influence>(_WasInfluencedBy_QNAME, Influence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Entity }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "entity")
    public JAXBElement<Entity> createEntity(Entity value) {
        return new JAXBElement<Entity>(_Entity_QNAME, Entity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyCollection }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "emptyCollection")
    public JAXBElement<EmptyCollection> createEmptyCollection(EmptyCollection value) {
        return new JAXBElement<EmptyCollection>(_EmptyCollection_QNAME, EmptyCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "role")
    public JAXBElement<Object> createRole(Object value) {
        return new JAXBElement<Object>(_Role_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bundle }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "bundle")
    public JAXBElement<Bundle> createBundle(Bundle value) {
        return new JAXBElement<Bundle>(_Bundle_QNAME, Bundle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "value")
    public JAXBElement<Object> createValue(Object value) {
        return new JAXBElement<Object>(_Value_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Alternate }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "alternateOf")
    public JAXBElement<Alternate> createAlternateOf(Alternate value) {
        return new JAXBElement<Alternate>(_AlternateOf_QNAME, Alternate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Activity }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "activity")
    public JAXBElement<Activity> createActivity(Activity value) {
        return new JAXBElement<Activity>(_Activity_QNAME, Activity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DictionaryMembership }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "hadDictionaryMember", substitutionHeadNamespace = "http://www.w3.org/ns/prov#", substitutionHeadName = "internalElement")
    public JAXBElement<DictionaryMembership> createHadDictionaryMember(DictionaryMembership value) {
        return new JAXBElement<DictionaryMembership>(_HadDictionaryMember_QNAME, DictionaryMembership.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link End }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasEndedBy")
    public JAXBElement<End> createWasEndedBy(End value) {
        return new JAXBElement<End>(_WasEndedBy_QNAME, End.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Document }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "document")
    public JAXBElement<Document> createDocument(Document value) {
        return new JAXBElement<Document>(_Document_QNAME, Document.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organization }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "organization")
    public JAXBElement<Organization> createOrganization(Organization value) {
        return new JAXBElement<Organization>(_Organization_QNAME, Organization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Membership }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "hadMember")
    public JAXBElement<Membership> createHadMember(Membership value) {
        return new JAXBElement<Membership>(_HadMember_QNAME, Membership.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Dictionary }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "dictionary", substitutionHeadNamespace = "http://www.w3.org/ns/prov#", substitutionHeadName = "internalElement")
    public JAXBElement<Dictionary> createDictionary(Dictionary value) {
        return new JAXBElement<Dictionary>(_Dictionary_QNAME, Dictionary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InternationalizedString }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "label")
    public JAXBElement<InternationalizedString> createLabel(InternationalizedString value) {
        return new JAXBElement<InternationalizedString>(_Label_QNAME, InternationalizedString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Agent }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "agent")
    public JAXBElement<Agent> createAgent(Agent value) {
        return new JAXBElement<Agent>(_Agent_QNAME, Agent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Invalidation }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasInvalidatedBy")
    public JAXBElement<Invalidation> createWasInvalidatedBy(Invalidation value) {
        return new JAXBElement<Invalidation>(_WasInvalidatedBy_QNAME, Invalidation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mention }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "mentionOf", substitutionHeadNamespace = "http://www.w3.org/ns/prov#", substitutionHeadName = "internalElement")
    public JAXBElement<Mention> createMentionOf(Mention value) {
        return new JAXBElement<Mention>(_MentionOf_QNAME, Mention.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Attribution }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasAttributedTo")
    public JAXBElement<Attribution> createWasAttributedTo(Attribution value) {
        return new JAXBElement<Attribution>(_WasAttributedTo_QNAME, Attribution.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Specialization }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "specializationOf")
    public JAXBElement<Specialization> createSpecializationOf(Specialization value) {
        return new JAXBElement<Specialization>(_SpecializationOf_QNAME, Specialization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Start }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasStartedBy")
    public JAXBElement<Start> createWasStartedBy(Start value) {
        return new JAXBElement<Start>(_WasStartedBy_QNAME, Start.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Removal }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "derivedByRemovalFrom", substitutionHeadNamespace = "http://www.w3.org/ns/prov#", substitutionHeadName = "internalElement")
    public JAXBElement<Removal> createDerivedByRemovalFrom(Removal value) {
        return new JAXBElement<Removal>(_DerivedByRemovalFrom_QNAME, Removal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Insertion }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "derivedByInsertionFrom", substitutionHeadNamespace = "http://www.w3.org/ns/prov#", substitutionHeadName = "internalElement")
    public JAXBElement<Insertion> createDerivedByInsertionFrom(Insertion value) {
        return new JAXBElement<Insertion>(_DerivedByInsertionFrom_QNAME, Insertion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Quotation }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasQuotedFrom")
    public JAXBElement<Quotation> createWasQuotedFrom(Quotation value) {
        return new JAXBElement<Quotation>(_WasQuotedFrom_QNAME, Quotation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyEntityPair }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "keyEntityPair", substitutionHeadNamespace = "http://www.w3.org/ns/prov#", substitutionHeadName = "internalElement")
    public JAXBElement<KeyEntityPair> createKeyEntityPair(KeyEntityPair value) {
        return new JAXBElement<KeyEntityPair>(_KeyEntityPair_QNAME, KeyEntityPair.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Plan }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "plan")
    public JAXBElement<Plan> createPlan(Plan value) {
        return new JAXBElement<Plan>(_Plan_QNAME, Plan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Generation }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "wasGeneratedBy")
    public JAXBElement<Generation> createWasGeneratedBy(Generation value) {
        return new JAXBElement<Generation>(_WasGeneratedBy_QNAME, Generation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BundleConstructor }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/ns/prov#", name = "bundleContent", scope = Document.class)
    public JAXBElement<BundleConstructor> createDocumentBundleContent(BundleConstructor value) {
        return new JAXBElement<BundleConstructor>(_DocumentBundleContent_QNAME, BundleConstructor.class,
                Document.class, value);
    }

}
